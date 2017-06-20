package com.soule.crm.pfm.dsl.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.soule.crm.pfm.dsl.ast.Function;
import com.soule.crm.pfm.dsl.ast.Language;
import com.soule.crm.pfm.dsl.ast.Operator;
import com.soule.crm.pfm.dsl.ast.TakeHolder;
import com.soule.crm.pfm.dsl.ast.base.BaseLanguage;

public class TokenParser {
    private HashMap start2Holder = new HashMap();

    public HashMap getStart2Holder() {
        return start2Holder;
    }

    private HashMap name2Function = new HashMap();

    public HashMap getName2Function() {
        return name2Function;
    }

    private HashMap name2Operator = new HashMap();

    public HashMap getName2Operator() {
        return name2Operator;
    }

    private Language lang;

    public TokenParser(Language lang) {
        this.lang = lang;
        List takeHolders = lang.getSupportedTakeHolders();
        Object ths[] = takeHolders.toArray();
        for (int i = 0; i < ths.length; i++) {
            TakeHolder th = (TakeHolder) ths[i];
            start2Holder.put(th.getStart(), th);
        }

        List operators = lang.getSupportedOperators();
        Object ops[] = operators.toArray();
        for (int i = 0; i < ops.length; i++) {
            Operator op = (Operator) ops[i];
            name2Operator.put(op.getWord(), op);
        }

        List functions = lang.getSupportedFunctions();
        Object fs[] = functions.toArray();
        for (int i = 0; i < fs.length; i++) {
            Function f = (Function) fs[i];
            name2Function.put(f.getName(), f);
        }

    }

    private static final char[] sepraters = new char[] { '+', '-', '*', '/',
            ',', '>', '=', '<', '!'

    };

    private static final char[] spaces = new char[] { ' ', '\t', '\n', '\r' };
    /**
     * 操作附号'+', '-', '*', '/',',', '>', '=', '<', '!'
     * @param c
     * @return
     */
    private boolean isSimpleOperator(char c) {
        for (int i = 0; i < sepraters.length; i++) {
            if (sepraters[i] == c)
                return true;
        }
        return false;
    }
    /**
     * 判断是否有分隔附，包括' ', '\t', '\n', '\r','+', '-', '*', '/', ',', '>', '=', '<', '!'
     * @param c
     * @return
     */
    private boolean isSeprator(char c) {

        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == c)
                return true;
        }

        if (isSimpleOperator(c))
            return true;

        return false;
    }

    private static final char OPENED_BRACKET = '(';
    private static final char CLOSED_BRACKET = ')';
    private static final char DOUBLE_QUOTE = '"';

    public static boolean isNumeric(String exp) {
        try {
            Double.parseDouble(exp);
            return true;
        } catch (Exception e) {

        }

        return false;
    }
    
    public static boolean isAssert(String exp) {
        if(exp == null){
            return false;
        }
        String tmp = exp.trim();
        return DOUBLE_QUOTE == tmp.charAt(0);

    }

    private boolean isTakeHolder(String exp) {
        if (exp.length() < 2)
            return false;

        return start2Holder.containsKey(exp.substring(0, 1));

    }

    private boolean isFunctionName(String exp) {
        if (exp.length() < 2)
            return false;

        return name2Function.containsKey(exp);
    }

    private boolean isOperator(String exp) {
        return name2Operator.containsKey(exp.toUpperCase());

    }

    private boolean isOperand(String exp) {
        return isTakeHolder(exp) || isNumeric(exp);

    }

    public List parseInternal(String exp) throws TokenException {
        // asert
        if (exp == null || exp.trim().length() == 0)
            return null;

        List tokens = new ArrayList();

        char[] chars = exp.toCharArray();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == OPENED_BRACKET) {
                int count = 0;
                count++;
                sb.append(c);
                boolean colsed = false;
                for (i = i + 1; i < chars.length; i++) {
                    char jc = chars[i];
                    sb.append(jc);
                    if (jc == OPENED_BRACKET) {
                        count++;

                        continue;
                    }
                    if (c == DOUBLE_QUOTE) {
                        //sb.append(c);
                        boolean qcolsed = false;
                        for (i = i + 1; i < chars.length; i++) {
                            char qc = chars[i];
                            sb.append(qc);
                            if (qc == DOUBLE_QUOTE) {
                                qcolsed = true;
                                break;
                            }
                        }
                        if (!qcolsed)
                            throw new TokenException("双引号不匹配");
                        continue;
                    }
                    if (jc == CLOSED_BRACKET) {
                        count--;
                        if (count == 0) {
                            colsed = true;
                            break;
                        }
                    }
                }
                if (!colsed)
                    throw new TokenException("括号不匹配");
                continue;
            }
            if (c == DOUBLE_QUOTE) {
                sb.append(c);
                boolean qcolsed = false;
                for (i = i + 1; i < chars.length; i++) {
                    char qc = chars[i];
                    sb.append(qc);
                    if (qc == DOUBLE_QUOTE) {
                        qcolsed = true;
                        break;
                    }
                }
                if (!qcolsed)
                    throw new TokenException("双引号不匹配");
                String tmp = sb.toString();
                if (tmp.length() != 0) {
                    Token t = new Token(Token.OPERAND, tmp);
                    tokens.add(t);
                }
                sb = new StringBuffer();
                continue;
            }
            if (c == ',') {
                String tmp = sb.toString();
                if (tmp.length() != 0) {
                    Token t = new Token(Token.EXPRESSION, tmp);
                    tokens.add(t);
                }
                sb = new StringBuffer();
                continue;
            }
            sb.append(c);
        }
        String tmp = sb.toString();
        if (tmp.length() > 0) {
            Token t = new Token(Token.EXPRESSION, tmp);
            tokens.add(t);
        }
        // tokens = mergeTokens(tokens);
        // validateTokens(tokens);
        return tokens;
    }

    public List<Token> parse(String exp) throws TokenException {
        // asert
        if (exp == null || exp.trim().length() == 0)
            return null;
        List<Token> tokens = new ArrayList<Token>();
        char[] chars = exp.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (isSeprator(c)) {
                String tmp = sb.toString();
                if (tmp.length() != 0) {
                    Token t = new Token(Token.EXPRESSION, tmp);
                    if (isOperand(tmp)) {
                        t.setType(Token.OPERAND);
                    }
                    tokens.add(t);
                }
                if (isSimpleOperator(c)) {
                    String op = "" + c;
                    Token top = new Token(Token.OPERATOR, op);
                    tokens.add(top);
                }
                sb = new StringBuffer();
                continue;
            }
            if (c == OPENED_BRACKET) {
                int count = 0;
                count++;
                sb.append(c);
                boolean colsed = false;
                for (i = i + 1; i < chars.length; i++) {
                    char jc = chars[i];
                    sb.append(jc);
                    if (jc == OPENED_BRACKET) {
                        count++;
                        continue;
                    }
                    if (c == DOUBLE_QUOTE) {
                        //sb.append(c);
                        boolean qcolsed = false;
                        for (i = i + 1; i < chars.length; i++) {
                            char qc = chars[i];
                            sb.append(qc);
                            if (qc == DOUBLE_QUOTE) {
                                qcolsed = true;
                                break;
                            }
                        }
                        if (!qcolsed)
                            throw new TokenException("双引号不匹配");
                        String tmp = sb.toString();
                        if (tmp.length() != 0) {
                            Token t = new Token(Token.OPERAND, tmp);
                            tokens.add(t);
                        }
                        sb = new StringBuffer();
                        continue;
                    }
                    if (jc == CLOSED_BRACKET) {
                        count--;
                        if (count == 0) {
                            colsed = true;
                            String tmp = sb.toString();
                            String name = tmp.substring(0, tmp
                                    .indexOf(OPENED_BRACKET));
                            if (name.length() == 0) {
                                Token t = new Token(Token.EXPRESSION, tmp);
                                tokens.add(t);

                            } else if (isFunctionName(name)) {
                                Token t = new Token(Token.FUNCTION, tmp);
                                tokens.add(t);

                            } else if (isOperator(name)) {
                                Token t = new Token(Token.OPERATOR, name);
                                tokens.add(t);

                                t = new Token(Token.EXPRESSION, tmp
                                        .substring(tmp.indexOf(OPENED_BRACKET)));
                                tokens.add(t);

                            } else {
                                throw new TokenException("不能识别的函数或操作符:" + name);
                            }
                            sb = new StringBuffer();

                            break;
                        }
                    }

                }
                if (!colsed)
                    throw new TokenException("括号不匹配");
                continue;
            }
            
            if (c == DOUBLE_QUOTE) {

                sb.append(c);
                boolean qcolsed = false;
                for (i = i + 1; i < chars.length; i++) {
                    char qc = chars[i];
                    sb.append(qc);

                    if (qc == DOUBLE_QUOTE) {

                        qcolsed = true;

                        break;
                    }

                }
                if (!qcolsed)
                    throw new TokenException("双引号不匹配");
                String tmp = sb.toString();
                if (tmp.length() != 0) {
                    Token t = new Token(Token.OPERAND, tmp);
                    tokens.add(t);

                }

                sb = new StringBuffer();
                continue;

            }
            
            sb.append(c);

        }
        String tmp = sb.toString();
        if (tmp.length() > 0) {
            Token t = new Token(Token.EXPRESSION, tmp);
            if (isOperand(tmp)) {
                t.setType(Token.OPERAND);
            }
            tokens.add(t);
        }
        if (tokens.size() == 1) {
            Token token = (Token) tokens.get(0);
            if (token.getType() == Token.EXPRESSION) {
                String tmpExp = token.getExp();
                if (tmpExp.charAt(0) == OPENED_BRACKET) {
                    String interal = tmpExp.substring(1, tmpExp.length() - 1);
                    return parse(interal);
                } else {
                    throw new TokenException("不能识别的操作数：" + tmpExp);
                }

            }
            if (token.getType() == Token.OPERATOR) {
                throw new TokenException("表达式尚未结束");
            }
        }

        tokens = mergeTokens(tokens);
        validateTokens(tokens);
        return tokens;
    }
    /**
     * 检查list中相连的token中type不能相同
     * @param inLst
     * @throws TokenException
     */
    private void validateTokens(List inLst) throws TokenException {
        Object[] ts = inLst.toArray();
        int length = ts.length;

        if (length > 1) {
            for (int i = 1; i < length; i++) {
                Token t = (Token) ts[i - 1];
                Token nt = (Token) ts[i];
                if (t.getType() == nt.getType()) {
                    throw new TokenException("\"" + t.getExp() + "\"和\""
                            + nt.getExp() + "\"不能连在一起使用");
                }
            }
        }

    }
    /**
     * 格式化list,如第一个Token为+或-且第二个Token为数字时合并为同一个Token,
     * 			 合并<=,<=,!=为同一个Token
     * @param inLst
     * @return
     */
    private List<Token> mergeTokens(List<Token> inLst) {
        List<Token> tokens = new ArrayList<Token>();
        Object[] ts = inLst.toArray();
        int length = ts.length;

        if (length > 1) {
            boolean finished = false;
            for (int i = 0; i < length - 1; i++) {
                Token t = (Token) ts[i];
                Token nt = (Token) ts[i + 1];
                if (t.getType() == Token.OPERATOR) {
                    String exp = t.getExp();
                    if (i == 0 && (exp.equals("+") || exp.equals("-"))
                            && nt.getType() == Token.OPERAND) {
                        if (isNumeric(nt.getExp())) {
                            Token token = new Token(Token.OPERAND, exp
                                    + nt.getExp());
                            tokens.add(token);
                            i++;
                            if (i == length - 1) {
                                finished = true;
                            }
                            continue;

                        }
                    }

                    if ((exp.equals(">") || exp.equals("<") || exp.equals("!"))
                            && nt.getExp().equals("=")) {

                        Token token = new Token(Token.OPERATOR, exp
                                + nt.getExp());
                        tokens.add(token);
                        i++;
                        if (i == length - 1) {
                            finished = true;
                        }

                        continue;

                    }

                }

                tokens.add(t);
            }
            if (!finished) {
                tokens.add(inLst.get(length - 1));
            }
        } else {
            tokens = inLst;
        }

        return tokens;
    }

    public Language getLanguage() {

        return lang;
    }

    public static void main(String[] args) {
        TokenParser tp = new TokenParser(new BaseLanguage());
        String exp = "-5+6 * (4 >= 7 * (3+7))>=6 + \"5 + 6 * (1 - 3)\"";
        try {
            List<Token> lst = tp.parse(exp);
            //Object[] ts = lst.toArray();
            for (Token t:lst) {
                System.out.println(t.getExp()+":"+t.getType());
            }
        } catch (TokenException e) {
            // e.printStackTrace();
        }
    }

}