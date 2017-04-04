package com.soule.crm.monitor.welcome.baseinfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;
import org.springframework.stereotype.Service;

import com.soule.base.service.ServiceException;

/**
 * 基本信息展示业务操作
 */
@Service
public class BaseinfoServiceImpl implements IBaseinfoService {

	private final static Log logger = LogFactory
			.getLog(BaseinfoServiceImpl.class);

	/**
	 * 查询系统配置信息
	 */
	public BaseinfoInitDataOut initData(BaseinfoInitDataIn in)
			throws ServiceException {
		BaseinfoInitDataOut out = new BaseinfoInitDataOut();
		BaseinfoAppPo appInfo = getBaseinfoAppPo();
		out.setApp(appInfo);

		BaseinfoHardwarePo hardware = getHardwarePo();
		out.setHardware(hardware);

		BaseinfoJvmPo jvminfo = getJvmPo();
		out.setJvm(jvminfo);

		return out;
	}

	private BaseinfoJvmPo getJvmPo() {
		BaseinfoJvmPo jvminfo = new BaseinfoJvmPo();
		Properties env = System.getProperties();
		jvminfo.setJvmName(env.getProperty("java.runtime.name", "unknown"));
		jvminfo.setJvmVersio(env.getProperty("java.runtime.version", "unknown"));
		jvminfo.setJvmVendor(env.getProperty("java.vm.vendor", "unknown"));
		jvminfo.setJvmAvlMe(String
				.valueOf(Runtime.getRuntime().freeMemory()/ 1024 / 1024) + "M");
		jvminfo.setJvmMemory(String
				.valueOf(Runtime.getRuntime().totalMemory() / 1024 / 1024) + "M");
		return jvminfo;
	}

	private BaseinfoHardwarePo getHardwarePo() {
		OperatingSystem sys = OperatingSystem.getInstance();
		BaseinfoHardwarePo hardware = new BaseinfoHardwarePo();
		hardware.setOsName(sys.getDescription());
		hardware.setHardwareAgre(sys.getArch());
		hardware.setOsVersion(sys.getPatchLevel());
		Shell sh = new Shell();
		try {
			long l = sh.getSigar().getMem().getTotal();
			hardware.setMemory(String.valueOf(l / 1024 / 1024) + "M");
			long fl = sh.getSigar().getMem().getFree();
			hardware.setFreeMemory(String.valueOf(fl / 1024 / 1024) + "M");
		} catch (SigarException e1) {
		}
		try {
			CpuInfo[] cpus = sh.getSigar().getCpuInfoList();
			org.hyperic.sigar.CpuInfo info = cpus[0];
			hardware.setCpuCount(String.valueOf(info.getTotalCores()));
		} catch (SigarException e) {
			hardware.setCpuCount("unknown");
		}
		hardware.setOsCharset(System.getProperty("file.encoding"));
		hardware.setOsLanguage(Locale.getDefault().toString());
		return hardware;
	}

	private BaseinfoAppPo getBaseinfoAppPo() {
		BaseinfoAppPo appInfo = new BaseinfoAppPo();
		appInfo.setMachineName(getHostName());
		appInfo.setMachineIp(getHostAddr());
		return appInfo;
	}

	private static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "unknown";
		}
	}

	private static String getHostAddr() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "unknown";
		}
	}
}
