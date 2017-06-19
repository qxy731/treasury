package com.soule.crm.pfm.dsl.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.soule.crm.pfm.dsl.ast.Function;
import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.Type;
import com.soule.crm.pfm.dsl.ast.base.AddOperator;
import com.soule.crm.pfm.dsl.ast.base.AndOperator;
import com.soule.crm.pfm.dsl.ast.base.DivOperator;
import com.soule.crm.pfm.dsl.ast.base.EqualOperator;
import com.soule.crm.pfm.dsl.ast.base.LargeThanOperator;
import com.soule.crm.pfm.dsl.ast.base.LargeThanOrEqualOperator;
import com.soule.crm.pfm.dsl.ast.base.LessThanOperator;
import com.soule.crm.pfm.dsl.ast.base.LessThanOrEqualOperator;
import com.soule.crm.pfm.dsl.ast.base.MultOperator;
import com.soule.crm.pfm.dsl.ast.base.NotEqualOperator;
import com.soule.crm.pfm.dsl.ast.base.NotOperator;
import com.soule.crm.pfm.dsl.ast.base.OrOperator;
import com.soule.crm.pfm.dsl.ast.base.SubOperator;
import com.soule.crm.pfm.dsl.parser.Token;
import com.soule.crm.pfm.dsl.parser.TokenException;
import com.soule.crm.pfm.dsl.parser.TokenParser;

public class Compiler {
    private TokenParser parser;

    private HashMap start2Holder;

    private HashMap name2Function;

    private HashMap name2Operator = new HashMap();

    public Compiler(TokenParser tp) {
        parser = tp;
        start2Holder = parser.getStart2Holder();
        name2Function = parser.getName2Function();
        name2Operator = parser.getName2Operator();

    }

    public static final Type BOOLEAN = new com.soule.crm.pfm.dsl.ast.base.Boolean();

    public static final Type NUMERIC = new com.soule.crm.pfm.dsl.ast.base.Numeric();
    
    public static final Type ASSERT = new com.soule.crm.pfm.dsl.ast.base.Assert();

    private void doOperand(Unit u, Token t, TakeHolderContainer container) throws TokenException {
        String exp = t.getExp();
        if (TokenParser.isNumeric(exp)) {
            u.returnedType = NUMERIC;
            u.type = Unit.NUMERIC;
            u.oprands.add(exp);
            return;
        }
        if (TokenParser.isAssert(exp)) {
            u.returnedType = ASSERT;
            u.type = Unit.ASSERT;
            u.oprands.add(exp);
            return;
        }
        String start = exp.substring(0, 1);
        TakeHolder th = (TakeHolder) start2Holder.get(start);
        String name = exp.substring(1);
        if (th.isValid(name)) {
            u.returnedType = th.getReturnedType();
            u.type = th.getType();
            u.ref = th;
            String translatedName = th.translate(name);
            u.oprands.add(th.translate(name));
            container.addTakeHolder(start, translatedName);
        }else{
            if(start.equals("#") || start.equals("@") || start.equals("~") || start.equals("^")){
                throw new TokenException("非法的指标名：" + name);
            } else if (start.equals("&")) {
            	throw new TokenException("非法的参数名：" + name);
            }
            
            throw new TokenException("非法的占位符：" + exp);
        }

    }

    private void doFunction(Unit u, Token t, TakeHolderContainer container)
            throws TokenException {
        String exp = t.getExp();
        String name = exp.substring(0, exp.indexOf('('));//取得函数名
        Function f = (Function) name2Function.get(name);

        u.operator = name;
        u.type = Unit.FUNCTION;
        u.ref = f;
        u.returnedType = f.getReturnedType();

        // parse in-function
        String internal = exp.substring(exp.indexOf('(') + 1, exp.length() - 1);
        List tokens = parser.parseInternal(internal);
        if(tokens == null) tokens = new ArrayList();
        //tokens = processFunctionTokens(tokens);
        if (f.getSupportedInputTypes().length != tokens.size()) {
            throw new TokenException("函数\"" + name + "\"参数数量不正确");
        }
        if (tokens.size() > 0) {
            Object[] ts = tokens.toArray();
            Type[] inputTypes = f.getSupportedInputTypes();
            for (int i = 0; i < ts.length; i++) {
                Token token = (Token) ts[i];
                if (token.getType() == Token.OPERAND) {
                    Unit pu = new Unit();
                    doOperand(pu, token, container);
                    if (inputTypes[i].getClass().isAssignableFrom(
                            pu.returnedType.getClass())) {
                        u.oprands.add(pu);
                    } else {
                        throw new TokenException("函数\"" + name + "\"参数类型不匹配");
                    }

                }
                if (token.getType() == Token.FUNCTION) {
                    Unit pu = new Unit();
                    doFunction(pu, token, container);
                    if (inputTypes[i].getClass().isAssignableFrom(
                            pu.returnedType.getClass())) {
                        u.oprands.add(pu);
                    } else {
                        throw new TokenException("函数\"" + name + "\"参数类型不匹配");
                    }

                }
                if (token.getType() == Token.OPERATOR) {
                    throw new TokenException("函数\"" + name + "\"含有非法参数");
                }
                if (token.getType() == Token.EXPRESSION) {
                    Unit pu = new Unit();
                    doExpression(pu, token, container);
                    if (inputTypes[i].getClass().isAssignableFrom(
                            pu.returnedType.getClass())) {
                        u.oprands.add(pu);
                    } else {
                        throw new TokenException("函数\"" + name + "\"参数类型不匹配");
                    }

                }
            }

            if (!f.validate(u.oprands.toArray())) {
                throw new TokenException("函数\"" + name + "\"参数取值不符合要求");
            }

        }

    }

    private void doExpression(Unit pu, Token token,
            TakeHolderContainer container) throws TokenException {
        String exp = token.getExp();

        List tokens = parser.parse(exp);
        Unit tu = compile(tokens, container);
        pu.operator = tu.operator;
        pu.oprands = tu.oprands;
        pu.ref = tu.ref;
        pu.returnedType = tu.returnedType;
        pu.type = tu.type;

    }

    public Unit compile(List tokens, TakeHolderContainer container)
            throws TokenException {
        Unit u = new Unit();
        Object[] ts = tokens.toArray();
        int length = ts.length;
        if (length == 1) {
            Token t = (Token) ts[0];
            if (t.getType() == Token.OPERATOR) {
                throw new TokenException("表达式没有结束，缺乏操作数");
            }
            if (t.getType() == Token.OPERAND) {
                doOperand(u, t, container);
            }
            if (t.getType() == Token.FUNCTION) {
                doFunction(u, t, container);
            }
            if (t.getType() == Token.EXPRESSION) {
                doExpression(u, t, container);
            }

            return u;
        }
        int top = findTopOperator(ts);
        if (top == -1) {
            throw new TokenException("表达式不完整");
        }
        // "Not" operator
        if (top == 0) {
            Token t = (Token) ts[0];
            u.operator = t.getExp();

            Object[] right = new Object[length - 1];
            System.arraycopy(ts, 1, right, 0, length - 1);
            List rl = Arrays.asList(right);

            Unit ru = this.compile(rl, container);
            String name = t.getExp().toUpperCase();
            Operator op = (Operator) name2Operator.get(name);
            if (op.getSupportedInputTypes()[0].getClass().isAssignableFrom(
                    ru.returnedType.getClass())) {
                u.oprands.add(ru);

                u.ref = op;
                u.returnedType = op.getReturnedType();
                u.type = Unit.OPERATION;
            } else {
                throw new TokenException("t.getExp().toUpperCase()"
                        + "操作符与之后相连的操作数类型不匹配");
            }

        }
        if (top > 0) {
            Token t = (Token) ts[top];
            u.operator = t.getExp();

            Object[] left = new Object[top];
            System.arraycopy(ts, 0, left, 0, top);

            Object[] right = new Object[length - top - 1];
            System.arraycopy(ts, top + 1, right, 0, length - top - 1);

            List ll = Arrays.asList(left);

            Unit lu = this.compile(ll, container);

            List rl = Arrays.asList(right);

            Unit ru = this.compile(rl, container);

            Operator op = (Operator) name2Operator
                    .get(t.getExp().toUpperCase());

            if (op.getSupportedInputTypes()[0].getClass().isAssignableFrom(
                    lu.returnedType.getClass())) {
                u.oprands.add(lu);
            } else {
                throw new TokenException("t.getExp().toUpperCase()"
                        + "操作符与之前相连的操作数类型不匹配");
            }

            if (op.getSupportedInputTypes()[1].getClass().isAssignableFrom(
                    ru.returnedType.getClass())) {
                u.oprands.add(ru);
            } else {
                throw new TokenException("t.getExp().toUpperCase()"
                        + "操作符与之后相连的操作数类型不匹配");
            }
            u.ref = op;
            u.returnedType = op.getReturnedType();
            u.type = Unit.OPERATION;
        }

        return u;
    }

    private List processFunctionTokens(List inLst) {
        int length = inLst.size();
        if (length == 0)
            return inLst;
        Object[] ts = inLst.toArray();
        StringBuffer sb = new StringBuffer();
        List tokens = new ArrayList();
        int j = 0;
        Token pre = null;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getExp().equals(",")) {
                j = 0;
                Token token = pre;
                if (j > 1) {
                    token = new Token(Token.EXPRESSION, sb.toString());
                }
                sb = new StringBuffer();
                tokens.add(token);
                pre = null;
                continue;
            }
            j++;
            pre = t;
            sb.append(exp);
        }
        if (sb.length() != 0) {
            if (((Token) ts[length - 1]).getExp().equals(sb.toString())) {
                tokens.add((Token) ts[length - 1]);
            } else {
                Token token = new Token(Token.EXPRESSION, sb.toString());
                tokens.add(token);
            }
        }
        return tokens;
    }
    private int findTopOperator(Object[] ts) throws TokenException {
        int length = ts.length;
        int ret = -1;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR && exp.equals(OrOperator.OR)) {
                if (i == 0) {
                    throw new TokenException("\"" + OrOperator.OR + "\"缺乏前置操作数");
                }
                if (i == length - 1) {
                    throw new TokenException("\"" + OrOperator.OR + "\"缺乏后置操作数");
                }
                ret =  i;
            }
        }
        if(ret != -1)return ret;

        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR && exp.equals(AndOperator.AND)) {
                if (i == 0) {
                    throw new TokenException("\"" + AndOperator.AND
                            + "\"缺乏前置操作数");
                }
                if (i == length - 1) {
                    throw new TokenException("\"" + AndOperator.AND
                            + "\"缺乏后置操作数");
                }
                ret = i;
            }
        }
        if(ret != -1)return ret;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR && exp.equals(NotOperator.NOT)) {
                if (i == length - 1) {
                    throw new TokenException("\"" + exp + "\"缺乏后置操作数");
                }
                ret =  i;
            }
        }
        if(ret != -1)return ret;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR
                    && (exp.equals(LargeThanOperator.LARGE_THAN)
                            || exp.equals(LessThanOperator.LESS_THAN)
                            || exp.equals(EqualOperator.EQUAL)
                            || exp
                                    .equals(LargeThanOrEqualOperator.LARGE_THAN_OR_EQUAL)
                            || exp
                                    .equals(LessThanOrEqualOperator.LESS_THAN_OR_EQUAL) || exp
                            .equals(NotEqualOperator.NOT_EQUAL))) {
                if (i == 0) {
                    throw new TokenException("\"" + exp + "\"缺乏前置操作数");
                }
                if (i == length - 1) {
                    throw new TokenException("\"" + exp + "\"缺乏后置操作数");
                }
                ret = i;
            }
        }
        if(ret != -1)return ret;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR
                    && (exp.equals(AddOperator.ADD) || exp
                            .equals(SubOperator.SUB)

                    )) {
                if (i == 0) {
                    throw new TokenException("\"" + exp + "\"缺乏前置操作数");
                }
                if (i == length - 1) {
                    throw new TokenException("\"" + exp + "\"缺乏后置操作数");
                }
                ret = i;
            }
        }
        if(ret != -1)return ret;
        for (int i = 0; i < length; i++) {
            Token t = (Token) ts[i];
            String exp = t.getExp();
            if (t.getType() == Token.OPERATOR
                    && (exp.equals(MultOperator.MULT) || exp
                            .equals(DivOperator.DIV)

                    )) {
                if (i == 0) {
                    throw new TokenException("\"" + exp + "\"缺乏前置操作数");
                }
                if (i == length - 1) {
                    throw new TokenException("\"" + exp + "\"缺乏后置操作数");
                }
                ret = i;
            }
        }
        if(ret != -1)return ret;
        return -1;
    }

}