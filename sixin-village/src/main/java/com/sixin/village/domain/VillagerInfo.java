package com.sixin.village.domain;

import com.sixin.common.annotation.Excel;
import com.sixin.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 村民表 villager_info
 *
 * @author ZX
 * @date 2019-09-21
 */
public class VillagerInfo extends BaseEntity
{

	private static final long serialVersionUID = 1L;

	public MultipartFile[] getPersonphotolist() {
		return personphotolist;
	}

	public void setPersonphotolist(MultipartFile[] personphotolist) {
		this.personphotolist = personphotolist;
	}

	public MultipartFile[] getHkphotolist() {
		return hkphotolist;
	}

	public void setHkphotolist(MultipartFile[] hkphotolist) {
		this.hkphotolist = hkphotolist;
	}

	public MultipartFile[] getIdphotolist() {
		return idphotolist;
	}

	public void setIdphotolist(MultipartFile[] idphotolist) {
		this.idphotolist = idphotolist;
	}

	public MultipartFile[] getCarphotolist() {
		return carphotolist;
	}

	public void setCarphotolist(MultipartFile[] carphotolist) {
		this.carphotolist = carphotolist;
	}

	public MultipartFile[] getHousephotolist() {
		return housephotolist;
	}

	public void setHousephotolist(MultipartFile[] housephotolist) {
		this.housephotolist = housephotolist;
	}
	private  MultipartFile[] personphotolist;
	private  MultipartFile[] hkphotolist;
	private  MultipartFile[] idphotolist;
	private  MultipartFile[] carphotolist;
	private  MultipartFile[] housephotolist;
	/** 数据编号 */
	private Integer vid;
	/** 地域编号 */
	@Excel(name = "地域编号")
	private String aid;
	/** 村民姓名 */
	@Excel(name = "村民姓名")
	private String vname;
	/** 年龄 */
	@Excel(name = "年龄")
	private Integer age;
	/** 性别 */
	@Excel(name = "性别")
	private String sex;
	/** 籍贯 */
	@Excel(name = "籍贯")
	private String nativeplace;
	/** 民族 */
	@Excel(name = "民族")
	private String nation;
	/** 政治面貌 */
	@Excel(name = "政治面貌")
	private String politics;
	/** 入党年月 */
	@Excel(name = "入党年月")
	private String partyjoindate;
	/** 文化程度 */
	@Excel(name = "文化程度")
	private String edulevel;
	/** 婚姻状况 */
	@Excel(name = "婚姻状况")
	private String marital;
	/** 身份证号码 */
	@Excel(name = "身份证号码")
	private String idcard;
	/** 联系方式 */
	@Excel(name = "联系方式")
	private String phone;
	/** 健康状况 */
	@Excel(name = "健康状况")
	private String health;
	/** 特种（残疾）信息 */
	@Excel(name = "特种（残疾）信息")
	private String special;
	/** 优抚信息 */
	@Excel(name = "优抚信息")
	private String entitle;
	/** 计生情况 */
	@Excel(name = "计生情况")
	private String familyplan;
	/** 医疗情况 */
	@Excel(name = "医疗情况")
	private String medical;
	/** 补助情况 */
	@Excel(name = "补助情况")
	private String subsidy;
	/** 保险情况 */
	@Excel(name = "保险情况")
	private String insurance;
	/** 犯罪史 */
	@Excel(name = "犯罪史")
	private String criminal;
	/** 吸毒史 */
	@Excel(name = "吸毒史")
	private String drug;
	/** 驾照 */
	@Excel(name = "驾照")
	private String drive;
	/** 车辆 */
	@Excel(name = "车辆")
	private String car;
	/** 车辆类型 */
	@Excel(name = "车辆类型")
	private String vtype;
	/** 车牌号 */
	@Excel(name = "车牌号")
	private String carnum;
	/** 职业 */
	@Excel(name = "职业")
	private String job;
	/** 工作单位 */
	@Excel(name = "工作单位")
	private String company;
	/** 户籍地址 */
	@Excel(name = "户籍地址")
	private String pra;
	/** 现居地址 */
	@Excel(name = "现居地址")
	private String home;
	/** 所属村组 */
	@Excel(name = "所属村组")
	private String grouptype;
	/** 住房性质 */
	@Excel(name = "住房性质")
	private String house;
	/** 身高 */
	@Excel(name = "身高")
	private Float height;
	/** 户主号 */
	@Excel(name = "户主号")
	private String hhdnum;
	/** 户主 */
	@Excel(name = "户主")
	private String hhd;
	/** 户主身份证号 */
	@Excel(name = "户主身份证号")
	private String hhdidcard;
	/** 与户主关系 */
	@Excel(name = "与户主关系")
	private String hhdrelation;
	/** 职务等级 */
	@Excel(name = "职务等级")
	private String jobrank;
	/** 爱好特长 */
	@Excel(name = "爱好特长")
	private String hobby;
	/** 思想动态 */
	@Excel(name = "思想动向")
	private String trend;
	/** 就业状态 */
	@Excel(name = "就业状态")
	private String jobstate;
	/** 大病医疗 */
	@Excel(name = "大病医疗")
	private String pr;
	/** 医保情况 */
	@Excel(name = "医保情况")
	private String medinsur;
	/** 新农保 */
	@Excel(name = "新农保")
	private String fmi;
	/** 新农保状态 */
	@Excel(name = "新农保状态")
	private String fmistate;
	/** 社保 */
	@Excel(name = "社保")
	private String security;
	/** 商业保险 */
	@Excel(name = "商业保险")
	private String bsecurity;
	/** 低保情况 */
	@Excel(name = "低保情况")
	private String subsis;
	/** 五保情况 */
	@Excel(name = "五保情况")
	private String fiveinsur;
	/** 特贫/困户 */
	@Excel(name = "特贫/困户")
	private String poor;
	/** 行驶证号 */
	@Excel(name = "行驶证号")
	private String drivenum;
	/** 技术类型 */
	@Excel(name = "技术类型")
	private String techtype;
	/** 技术等级 */
	@Excel(name = "技术等级")
	private String techgrade;
	/** 职称等级 */
	@Excel(name = "职称等级")
	private String titgrade;
	/** 户口性质 */
	@Excel(name = "户口性质")
	private String prt;
	/** 是否迁移（地址） */
	@Excel(name = "是否迁移（地址）")
	private Integer move;
	/** 田地面积 */
	@Excel(name = "田地面积")
	private Float fieldarea;
	/** 林地面积 */
	@Excel(name = "林地面积")
	private Float forestarea;
	/** 水塘面积 */
	@Excel(name = "水塘面积")
	private Float poolarea;
	/** 家庭收入 */
	@Excel(name = "家庭收入")
	private BigDecimal income;
	/** 个人财产总额 */
	@Excel(name = "个人财产总额")
	private BigDecimal totalppt;
	/** 月收入 */
	@Excel(name = "月收入")
	private BigDecimal mincome;
	/** 年收入 */
	@Excel(name = "年收入")
	private BigDecimal yincome;
	/** 银行 */
	@Excel(name = "银行")
	private String bank;
	/** 银行卡号 */
	@Excel(name = "银行卡号")
	private String bankcardnum;
	/** 土地确权 */
	@Excel(name = "土地确权")
	private String landowner;
	/** 综治 */
	@Excel(name = "综治")
	private String control;
	/** 房屋面积 */
	@Excel(name = "房屋面积")
	private Float housearea;
	/** 村务宝典登录名 */
	@Excel(name = "村务宝典登录名")
	private String loginname;
	/** 个体经营 */
	@Excel(name = "个体经营")
	private String selfmanage;
	/** 经营类型 */
	@Excel(name = "经营类型")
	private String managetype;
	/** 投入成本 */
	@Excel(name = "投入成本")
	private BigDecimal cost;
	/** 经营月收入 */
	@Excel(name = "经营月收入")
	private BigDecimal mmincome;
	/** 经营年收入 */
	@Excel(name = "经营年收入")
	private BigDecimal myincome;
	/** 所在组织/社团 */
	@Excel(name = "所在组织/社团")
	private String organization;
	/** 是否土地流转 */
	@Excel(name = "是否土地流转")
	private Integer landtransfer;
	/** 土地流转收入 */
	@Excel(name = "土地流转收入")
	private BigDecimal landincome;
	/** 是否林地流转 */
	@Excel(name = "是否林地流转")
	private Integer foresttransfer;
	/** 林地流转收入 */
	@Excel(name = "林地流转收入")
	private BigDecimal forestincome;
	/** 是否征收 */
	@Excel(name = "是否征收")
	private Integer levy;
	/** 房租收入 */
	@Excel(name = "房租收入")
	private BigDecimal rent;
	/** 房屋使用状态 */
	@Excel(name = "房屋使用状态")
	private String housestate;
	/** 是否接入电力 */
	@Excel(name = "是否接入电力")
	private Integer psa;
	/** 是否接入有线电视 */
	@Excel(name = "是否接入有线电视")
	private Integer ca;
	/** 是否接入宽带 */
	@Excel(name = "是否接入宽带")
	private Integer woban;
	/** 是否接入自来水 */
	@Excel(name = "是否接入自来水")
	private Integer water;
	/** 是否接入安防系统 */
	@Excel(name = "是否接入安防系统")
	private Integer ssa;
	/** 是否接入燃气 */
	@Excel(name = "是否接入燃气")
	private Integer gfs;
	/** 是否垃圾分类 */
	@Excel(name = "是否垃圾分类")
	private Integer wdr;
	/** 道路是否到户 */
	@Excel(name = "道路是否到户")
	private Integer rth;
	/** '是否行政干部' */
	@Excel(name = "是否行政干部")
	private Integer iscadre;
	/** '行政干部级别' */
	@Excel(name = "行政干部级别")
	private String cadrelevel;
	/** '是否低保户' */
	@Excel(name = "是否低保户")
	private Integer islowincome;
	/** '获得低保时间' */
	@Excel(name = "获得低保时间")
	private Date getlowdate;
	/** '是否复员军人' */
	@Excel(name = "是否复员军人")
	private Integer isdemob;
	/** '复员时间' */
	@Excel(name = "复员时间")
	private Date demobdate;
	/** 获奖情况 */
	@Excel(name = "获奖情况")
	private String winaward;
	/** 处分情况 */
	@Excel(name = "处分情况")
	private String punish;
	/** 个人照片 */
	@Excel(name= "个人照片" )
	private String personphoto;
	/** 户口照片 */
	@Excel(name= "户口照片" )
	private String hkphoto;
	/** 身份证照片 */
	@Excel(name= "身份证照片" )
	private String idphoto;
	/** 汽车照片 */
	@Excel(name= "汽车照片" )
	private String carphoto;

	/** 房屋照片 */
	@Excel(name= "房屋照片" )
	private String housephoto;
	/** 是否有效 */
	private Integer isuse;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** 用户登陆密码 */
	@Excel(name = "密码")
	private String password;

	public String getPersonphoto() {
		return personphoto;
	}

	public void setPersonphoto(String personphoto) {
		this.personphoto = personphoto;
	}

	public String getHkphoto() {
		return hkphoto;
	}

	public void setHkphoto(String hkphoto) {
		this.hkphoto = hkphoto;
	}

	public String getIdphoto() {
		return idphoto;
	}

	public void setIdphoto(String idphoto) {
		this.idphoto = idphoto;
	}

	public String getCarphoto() {
		return carphoto;
	}

	public void setCarphoto(String carphoto) {
		this.carphoto = carphoto;
	}

	public String getHousephoto() {
		return housephoto;
	}

	public void setHousephoto(String housephoto) {
		this.housephoto = housephoto;
	}



	public void setVid(Integer vid)
	{
		this.vid = vid;
	}

	public Integer getVid()
	{
		return vid;
	}
	public void setAid(String aid)
	{
		this.aid = aid;
	}

	public String getAid()
	{
		return aid;
	}
	public void setVname(String vname)
	{
		this.vname = vname;
	}

	public String getVname()
	{
		return vname;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}

	public Integer getAge()
	{
		return age;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getSex()
	{
		return sex;
	}
	public void setNativeplace(String nativeplace)
	{
		this.nativeplace = nativeplace;
	}

	public String getNativeplace()
	{
		return nativeplace;
	}
	public void setNation(String nation)
	{
		this.nation = nation;
	}

	public String getNation()
	{
		return nation;
	}
	public void setPolitics(String politics)
	{
		this.politics = politics;
	}

	public String getPolitics()
	{
		return politics;
	}
	public void setPartyjoindate(String partyjoindate)
	{
		this.partyjoindate = partyjoindate;
	}

	public String getPartyjoindate()
	{
		return partyjoindate;
	}
	public void setEdulevel(String edulevel)
	{
		this.edulevel = edulevel;
	}

	public String getEdulevel()
	{
		return edulevel;
	}
	public void setMarital(String marital)
	{
		this.marital = marital;
	}

	public String getMarital()
	{
		return marital;
	}
	public void setIdcard(String idcard)
	{
		this.idcard = idcard;
	}

	public String getIdcard()
	{
		return idcard;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return phone;
	}
	public void setHealth(String health)
	{
		this.health = health;
	}

	public String getHealth()
	{
		return health;
	}
	public void setSpecial(String special)
	{
		this.special = special;
	}

	public String getSpecial()
	{
		return special;
	}
	public void setEntitle(String entitle)
	{
		this.entitle = entitle;
	}

	public String getEntitle()
	{
		return entitle;
	}
	public void setFamilyplan(String familyplan)
	{
		this.familyplan = familyplan;
	}

	public String getFamilyplan()
	{
		return familyplan;
	}
	public void setMedical(String medical)
	{
		this.medical = medical;
	}

	public String getMedical()
	{
		return medical;
	}
	public void setSubsidy(String subsidy)
	{
		this.subsidy = subsidy;
	}

	public String getSubsidy()
	{
		return subsidy;
	}
	public void setInsurance(String insurance)
	{
		this.insurance = insurance;
	}

	public String getInsurance()
	{
		return insurance;
	}
	public void setCriminal(String criminal)
	{
		this.criminal = criminal;
	}

	public String getCriminal()
	{
		return criminal;
	}
	public void setDrug(String drug)
	{
		this.drug = drug;
	}

	public String getDrug()
	{
		return drug;
	}
	public void setDrive(String drive)
	{
		this.drive = drive;
	}

	public String getDrive()
	{
		return drive;
	}
	public void setCar(String car)
	{
		this.car = car;
	}

	public String getCar()
	{
		return car;
	}
	public void setVtype(String vtype)
	{
		this.vtype = vtype;
	}

	public String getVtype()
	{
		return vtype;
	}
	public void setCarnum(String carnum)
	{
		this.carnum = carnum;
	}

	public String getCarnum()
	{
		return carnum;
	}
	public void setJob(String job)
	{
		this.job = job;
	}

	public String getJob()
	{
		return job;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getCompany()
	{
		return company;
	}
	public void setPra(String pra)
	{
		this.pra = pra;
	}

	public String getPra()
	{
		return pra;
	}
	public void setHome(String home)
	{
		this.home = home;
	}

	public String getHome()
	{
		return home;
	}
	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}

	public String getGrouptype()
	{
		return grouptype;
	}
	public void setHouse(String house)
	{
		this.house = house;
	}

	public String getHouse()
	{
		return house;
	}
	public void setHeight(Float height)
	{
		this.height = height;
	}

	public Float getHeight()
	{
		return height;
	}
	public void setHhdnum(String hhdnum)
	{
		this.hhdnum = hhdnum;
	}

	public String getHhdnum()
	{
		return hhdnum;
	}
	public void setHhd(String hhd)
	{
		this.hhd = hhd;
	}

	public String getHhd()
	{
		return hhd;
	}
	public void setHhdidcard(String hhdidcard)
	{
		this.hhdidcard = hhdidcard;
	}

	public String getHhdidcard()
	{
		return hhdidcard;
	}
	public void setHhdrelation(String hhdrelation)
	{
		this.hhdrelation = hhdrelation;
	}

	public String getHhdrelation()
	{
		return hhdrelation;
	}
	public void setJobrank(String jobrank)
	{
		this.jobrank = jobrank;
	}

	public String getJobrank()
	{
		return jobrank;
	}
	public void setHobby(String hobby)
	{
		this.hobby = hobby;
	}

	public String getHobby()
	{
		return hobby;
	}
	public void setTrend(String trend)
	{
		this.trend = trend;
	}

	public String getTrend()
	{
		return trend;
	}
	public void setJobstate(String jobstate)
	{
		this.jobstate = jobstate;
	}

	public String getJobstate()
	{
		return jobstate;
	}
	public void setPr(String pr)
	{
		this.pr = pr;
	}

	public String getPr()
	{
		return pr;
	}
	public void setMedinsur(String medinsur)
	{
		this.medinsur = medinsur;
	}

	public String getMedinsur()
	{
		return medinsur;
	}
	public void setFmi(String fmi)
	{
		this.fmi = fmi;
	}

	public String getFmi()
	{
		return fmi;
	}
	public void setFmistate(String fmistate)
	{
		this.fmistate = fmistate;
	}

	public String getFmistate()
	{
		return fmistate;
	}
	public void setSecurity(String security)
	{
		this.security = security;
	}

	public String getSecurity()
	{
		return security;
	}
	public void setBsecurity(String bsecurity)
	{
		this.bsecurity = bsecurity;
	}

	public String getBsecurity()
	{
		return bsecurity;
	}
	public void setSubsis(String subsis)
	{
		this.subsis = subsis;
	}

	public String getSubsis()
	{
		return subsis;
	}
	public void setFiveinsur(String fiveinsur)
	{
		this.fiveinsur = fiveinsur;
	}

	public String getFiveinsur()
	{
		return fiveinsur;
	}
	public void setPoor(String poor)
	{
		this.poor = poor;
	}

	public String getPoor()
	{
		return poor;
	}
	public void setDrivenum(String drivenum)
	{
		this.drivenum = drivenum;
	}

	public String getDrivenum()
	{
		return drivenum;
	}
	public void setTechtype(String techtype)
	{
		this.techtype = techtype;
	}

	public String getTechtype()
	{
		return techtype;
	}
	public void setTechgrade(String techgrade)
	{
		this.techgrade = techgrade;
	}

	public String getTechgrade()
	{
		return techgrade;
	}
	public void setTitgrade(String titgrade)
	{
		this.titgrade = titgrade;
	}

	public String getTitgrade()
	{
		return titgrade;
	}
	public void setPrt(String prt)
	{
		this.prt = prt;
	}

	public String getPrt()
	{
		return prt;
	}
	public void setMove(Integer move)
	{
		this.move = move;
	}

	public Integer getMove()
	{
		return move;
	}
	public void setFieldarea(Float fieldarea)
	{
		this.fieldarea = fieldarea;
	}

	public Float getFieldarea()
	{
		return fieldarea;
	}
	public void setForestarea(Float forestarea)
	{
		this.forestarea = forestarea;
	}

	public Float getForestarea()
	{
		return forestarea;
	}
	public void setPoolarea(Float poolarea)
	{
		this.poolarea = poolarea;
	}

	public Float getPoolarea()
	{
		return poolarea;
	}
	public void setIncome(BigDecimal income)
	{
		this.income = income;
	}

	public BigDecimal getIncome()
	{
		return income;
	}
	public void setTotalppt(BigDecimal totalppt)
	{
		this.totalppt = totalppt;
	}

	public BigDecimal getTotalppt()
	{
		return totalppt;
	}
	public void setMincome(BigDecimal mincome)
	{
		this.mincome = mincome;
	}

	public BigDecimal getMincome()
	{
		return mincome;
	}
	public void setYincome(BigDecimal yincome)
	{
		this.yincome = yincome;
	}

	public BigDecimal getYincome()
	{
		return yincome;
	}
	public void setBank(String bank)
	{
		this.bank = bank;
	}

	public String getBank()
	{
		return bank;
	}
	public void setBankcardnum(String bankcardnum)
	{
		this.bankcardnum = bankcardnum;
	}

	public String getBankcardnum()
	{
		return bankcardnum;
	}
	public void setLandowner(String landowner)
	{
		this.landowner = landowner;
	}

	public String getLandowner()
	{
		return landowner;
	}
	public void setControl(String control)
	{
		this.control = control;
	}

	public String getControl()
	{
		return control;
	}
	public void setHousearea(Float housearea)
	{
		this.housearea = housearea;
	}

	public Float getHousearea()
	{
		return housearea;
	}
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	public String getLoginname()
	{
		return loginname;
	}
	public void setSelfmanage(String selfmanage)
	{
		this.selfmanage = selfmanage;
	}

	public String getSelfmanage()
	{
		return selfmanage;
	}
	public void setManagetype(String managetype)
	{
		this.managetype = managetype;
	}

	public String getManagetype()
	{
		return managetype;
	}
	public void setCost(BigDecimal cost)
	{
		this.cost = cost;
	}

	public BigDecimal getCost()
	{
		return cost;
	}
	public void setMmincome(BigDecimal mmincome)
	{
		this.mmincome = mmincome;
	}

	public BigDecimal getMmincome()
	{
		return mmincome;
	}
	public void setMyincome(BigDecimal myincome)
	{
		this.myincome = myincome;
	}

	public BigDecimal getMyincome()
	{
		return myincome;
	}
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	public String getOrganization()
	{
		return organization;
	}
	public void setLandtransfer(Integer landtransfer)
	{
		this.landtransfer = landtransfer;
	}

	public Integer getLandtransfer()
	{
		return landtransfer;
	}
	public void setLandincome(BigDecimal landincome)
	{
		this.landincome = landincome;
	}

	public BigDecimal getLandincome()
	{
		return landincome;
	}
	public void setForesttransfer(Integer foresttransfer)
	{
		this.foresttransfer = foresttransfer;
	}

	public Integer getForesttransfer()
	{
		return foresttransfer;
	}
	public void setForestincome(BigDecimal forestincome)
	{
		this.forestincome = forestincome;
	}

	public BigDecimal getForestincome()
	{
		return forestincome;
	}
	public void setLevy(Integer levy)
	{
		this.levy = levy;
	}

	public Integer getLevy()
	{
		return levy;
	}
	public void setRent(BigDecimal rent)
	{
		this.rent = rent;
	}

	public BigDecimal getRent()
	{
		return rent;
	}
	public void setHousestate(String housestate)
	{
		this.housestate = housestate;
	}

	public String getHousestate()
	{
		return housestate;
	}
	public void setPsa(Integer psa)
	{
		this.psa = psa;
	}

	public Integer getPsa()
	{
		return psa;
	}
	public void setCa(Integer ca)
	{
		this.ca = ca;
	}

	public Integer getCa()
	{
		return ca;
	}
	public void setWoban(Integer woban)
	{
		this.woban = woban;
	}

	public Integer getWoban()
	{
		return woban;
	}
	public void setWater(Integer water)
	{
		this.water = water;
	}

	public Integer getWater()
	{
		return water;
	}
	public void setSsa(Integer ssa)
	{
		this.ssa = ssa;
	}

	public Integer getSsa()
	{
		return ssa;
	}
	public void setGfs(Integer gfs)
	{
		this.gfs = gfs;
	}

	public Integer getGfs()
	{
		return gfs;
	}
	public void setWdr(Integer wdr)
	{
		this.wdr = wdr;
	}

	public Integer getWdr()
	{
		return wdr;
	}
	public void setRth(Integer rth)
	{
		this.rth = rth;
	}

	public Integer getRth()
	{
		return rth;
	}
	public void setWinaward(String winaward)
	{
		this.winaward = winaward;
	}

	public void setIscadre(Integer iscadre)
	{
		this.iscadre = iscadre;
	}

	public Integer getIscadre()
	{
		return iscadre;
	}
	public void setCadrelevel(String cadrelevel)
	{
		this.cadrelevel = cadrelevel;
	}

	public String getCadrelevel()
	{
		return cadrelevel;
	}
	public void setIslowincome(Integer islowincome)
	{
		this.islowincome = islowincome;
	}

	public Integer getIslowincome()
	{
		return islowincome;
	}
	public void setGetlowdate(Date getlowdate)
	{
		this.getlowdate = getlowdate;
	}

	public Date getGetlowdate()
	{
		return getlowdate;
	}
	public void setIsdemob(Integer isdemob)
	{
		this.isdemob = isdemob;
	}

	public Integer getIsdemob()
	{
		return isdemob;
	}
	public void setDemobdate(Date demobdate)
	{
		this.demobdate = demobdate;
	}

	public Date getDemobdate()
	{
		return demobdate;
	}

	public String getWinaward()
	{
		return winaward;
	}
	public void setPunish(String punish)
	{
		this.punish = punish;
	}

	public String getPunish()
	{
		return punish;
	}
	public void setIsuse(Integer isuse)
	{
		this.isuse = isuse;
	}

	public Integer getIsuse()
	{
		return isuse;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("vid", getVid())
				.append("aid", getAid())
				.append("vname", getVname())
				.append("age", getAge())
				.append("sex", getSex())
				.append("nativeplace", getNativeplace())
				.append("nation", getNation())
				.append("politics", getPolitics())
				.append("partyjoindate", getPartyjoindate())
				.append("edulevel", getEdulevel())
				.append("marital", getMarital())
				.append("idcard", getIdcard())
				.append("phone", getPhone())
				.append("health", getHealth())
				.append("special", getSpecial())
				.append("entitle", getEntitle())
				.append("familyplan", getFamilyplan())
				.append("medical", getMedical())
				.append("subsidy", getSubsidy())
				.append("insurance", getInsurance())
				.append("criminal", getCriminal())
				.append("drug", getDrug())
				.append("drive", getDrive())
				.append("car", getCar())
				.append("vtype", getVtype())
				.append("carnum", getCarnum())
				.append("job", getJob())
				.append("company", getCompany())
				.append("pra", getPra())
				.append("home", getHome())
				.append("grouptype", getGrouptype())
				.append("house", getHouse())
				.append("height", getHeight())
				.append("hhdnum", getHhdnum())
				.append("hhd", getHhd())
				.append("hhdidcard", getHhdidcard())
				.append("hhdrelation", getHhdrelation())
				.append("jobrank", getJobrank())
				.append("hobby", getHobby())
				.append("trend", getTrend())
				.append("jobstate", getJobstate())
				.append("pr", getPr())
				.append("medinsur", getMedinsur())
				.append("fmi", getFmi())
				.append("fmistate", getFmistate())
				.append("security", getSecurity())
				.append("bsecurity", getBsecurity())
				.append("subsis", getSubsis())
				.append("fiveinsur", getFiveinsur())
				.append("poor", getPoor())
				.append("drivenum", getDrivenum())
				.append("techtype", getTechtype())
				.append("techgrade", getTechgrade())
				.append("titgrade", getTitgrade())
				.append("prt", getPrt())
				.append("move", getMove())
				.append("fieldarea", getFieldarea())
				.append("forestarea", getForestarea())
				.append("poolarea", getPoolarea())
				.append("income", getIncome())
				.append("totalppt", getTotalppt())
				.append("mincome", getMincome())
				.append("yincome", getYincome())
				.append("bank", getBank())
				.append("bankcardnum", getBankcardnum())
				.append("landowner", getLandowner())
				.append("control", getControl())
				.append("housearea", getHousearea())
				.append("loginname", getLoginname())
				.append("selfmanage", getSelfmanage())
				.append("managetype", getManagetype())
				.append("cost", getCost())
				.append("mmincome", getMmincome())
				.append("myincome", getMyincome())
				.append("organization", getOrganization())
				.append("landtransfer", getLandtransfer())
				.append("landincome", getLandincome())
				.append("foresttransfer", getForesttransfer())
				.append("forestincome", getForestincome())
				.append("levy", getLevy())
				.append("rent", getRent())
				.append("housestate", getHousestate())
				.append("psa", getPsa())
				.append("ca", getCa())
				.append("woban", getWoban())
				.append("water", getWater())
				.append("ssa", getSsa())
				.append("gfs", getGfs())
				.append("wdr", getWdr())
				.append("rth", getRth())
				.append("winaward", getWinaward())
				.append("punish", getPunish())
				.append("personphoto", getPersonphoto())
				.append("hkphoto", getPunish())
				.append("idphoto", getIdphoto())
				.append("carphoto", getCarphoto())
				.append("housephoto", getHousephoto())
				.append("isuse", getIsuse())
				.append("password", getPassword())
				.toString();
	}
}
