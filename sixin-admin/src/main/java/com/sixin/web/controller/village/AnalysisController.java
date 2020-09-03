package com.sixin.web.controller.village;

import com.sixin.framework.web.base.BaseController;
import com.sixin.village.domain.VillagerInfo;
import com.sixin.village.service.IVillagerInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: ZX
 * @Description:
 * @Date: Create in 11:10 2019/11/10
 */
@Controller
@RequestMapping("/village/analysis")
public class AnalysisController extends BaseController {

    private String prefix = "village/analysis";

    @Autowired
    private IVillagerInfoService villagerInfoService;

    @RequiresPermissions("village:analysis:view")
    @GetMapping()
    public String villagerInfo()
    {
        return prefix + "/analysis";
    }

    /**
     * 查询村民并分析比例
     */
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count")
    @ResponseBody
    public String Groupsexy(String aid,String newtime,String startTime, String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer fnum=0;//男数量
        Integer fmnum=0;//女数量
        String idcardnum1="";
        for(int i=0;i<count;i++){
            idcardnum1=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum1);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                if("男".equals(list.get(i).getSex())) {
                    fnum++;
                }else{
                    fmnum++;
                }

            }}
        Integer agenum1=0; //年龄大于六十
        Integer agenum2=0; //年龄小于十
        Integer agenum3=0; //年龄大于十小于六十
        String idcardnum="";
        for(int k=0;k<count;k++){
            idcardnum=list.get(k).getIdcard();
            if(getAgeByIDNumber(idcardnum)>60){
                agenum1++;
            }
            else if(getAgeByIDNumber(idcardnum)<10){
                agenum2++;
            }
            else if(getAgeByIDNumber(idcardnum)>10&&getAgeByIDNumber(idcardnum)<60){
                agenum3++;
            }

        }
        Integer isdemob=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(int j=0;j<count;j++){
            if(list.get(j).getDemobdate()!=null){


                Date date=list.get(j).getDemobdate();
                String demodate=sdf.format(date);
                demodate=demodate.substring(0,4);
                if((list.get(j).getIsdemob()==1)&&yearMonthBetween(demodate,"2018","2019")) {
                    isdemob++;
                }
            }
        }
        Integer islowingcome=0;
        for(int z=0;z<count;z++){

            if(list.get(z).getIslowincome()==1){
                islowingcome++;
            }
        }
        String result;
        if(newtime==""){
            result=count.toString()+'|'+fnum.toString()+'|'+ fmnum.toString()+'|'+agenum1.toString()+'|'+agenum2.toString()+'|'+agenum3.toString()+'|'+isdemob.toString()+'|'+islowingcome.toString();
        }else {
            Integer agenum4 = 0; //新增人口
            for (int o = 0; o < count; o++) {
                idcardnum = list.get(o).getIdcard();
                String date2 = extractYearMonthDayOfIdCard(idcardnum);
                if (newtime.compareTo(date2) < 0) {
                    agenum4++;
                }

            }
            result=count.toString()+'|'+fnum.toString()+'|'+ fmnum.toString()+'|'+agenum1.toString()+'|'+agenum2.toString()+'|'+agenum3.toString()+'|'+isdemob.toString()+'|'+islowingcome.toString()+'|'+agenum4.toString();
        }


        return result;
    }

    private static final int invalidAge = -1;//非法的年龄，用于处理异常。
    //身份证获取年龄
    public static int getAgeByIDNumber(String idNumber) {
        String dateStr;
        if (idNumber.length() == 15) {
            dateStr = "19" + idNumber.substring(6, 12);
        } else if (idNumber.length() == 18) {
            dateStr = idNumber.substring(6, 14);
        } else {//默认是合法身份证号，但不排除有意外发生
            return invalidAge;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date birthday = simpleDateFormat.parse(dateStr);
            return getAgeByDate(birthday);
        } catch (ParseException e) {
            return invalidAge;
        }


    }

    public static int getAgeByDate(Date birthday) {
        Calendar calendar = Calendar.getInstance();

        //calendar.before()有的点bug
        if (calendar.getTimeInMillis() - birthday.getTime() < 0L) {
            return invalidAge;
        }


        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH);
        int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(birthday);


        int yearBirthday = calendar.get(Calendar.YEAR);
        int monthBirthday = calendar.get(Calendar.MONTH);
        int dayOfMonthBirthday = calendar.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirthday;


        if (monthNow <= monthBirthday && monthNow == monthBirthday && dayOfMonthNow < dayOfMonthBirthday || monthNow < monthBirthday) {
            age--;
        }

        return age;
    }


    public static boolean yearMonthBetween(String nowDate, String startDate, String endDate) {

        int now = Integer.parseInt(nowDate);
        int start=Integer.parseInt(startDate);
        int end=Integer.parseInt(endDate);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date now = format.parse(nowDate);
//        Date start = format.parse(startDate);
//        Date end = format.parse(endDate);
//
//        long nowTime = now.getTime();
//        long startTime = start.getTime();
//        long endTime = end.getTime();

        return now >= start && now <= end;
    }

    /**
     * 身份证的正则表达式^(\d{15}|\d{17}[\dx])$
     * @param id    身份证号获取出生年月
     * @return    生日（yyyy-MM-dd）
     */
    public String extractYearMonthDayOfIdCard(String id) {
        String year = null;
        String month = null;
        String day = null;
        //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (id.matches("^\\d{15}|\\d{17}[\\dxX]$")) {
            year = id.substring(6, 10);
            month = id.substring(10,12);
            day = id.substring(12,14);
//        }else {
//            System.out.println("身份证号码不匹配！");
//            return null;
        }
        return year + "-" + month + "-" + day;
    }


    /**
     * String转Date
     *
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    //统计教育水平
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count1")
    @ResponseBody
    public String Grouplevel(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer edu=0;//男数量
        Integer edu2=0;//女数量
        Integer edu3=0;
        Integer edu4=0;
        Integer edu5=0;
        Integer edu6=0;
        Integer edu7=0;
        Integer other=0;
        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("本科".equals(list.get(i).getEdulevel())) {
                    edu++;
                }else if("研究生".equals(list.get(i).getEdulevel())){
                    edu2++;
                }else if("大专".equals(list.get(i).getEdulevel())){
                    edu3++;
                }else if("中专".equals(list.get(i).getEdulevel())){
                    edu4++;
                }else if("高中".equals(list.get(i).getEdulevel())){
                    edu5++;
                }else if("初中".equals(list.get(i).getEdulevel())){
                    edu6++;
                }else if("小学".equals(list.get(i).getEdulevel())){
                    edu7++;
                }else {
                    other++;
                }

            }}
        String result=count1.toString()+'|'+edu.toString()+'|'+ edu2.toString()+'|'+edu3.toString()+'|'+edu4.toString()+'|'+edu5.toString()+'|'+edu6.toString()+'|'+edu7.toString()+'|'+other.toString();

        return result;
    }
    //统计健康情况
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count2")
    @ResponseBody
    public String Grouphealth(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer fnum=0;//男数量
        Integer fnum1=0;//男健康数量
        Integer fnum2=0;//男不健康数量
        Integer fmnum=0;//女数量
        Integer fmnum1=0;//女健康数量
        Integer fmnum2=0;//女不健康数量
        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("男".equals(list.get(i).getSex())) {
                    fnum++;
                    if("健康".equals(list.get(i).getHealth()))
                        fnum1++;
                    else
                        fnum2++;
                }else{
                    fmnum++;
                    if("健康".equals(list.get(i).getHealth()))
                        fmnum1++;
                    else
                        fmnum2++;
                }

            }}
        String result=count1.toString()+'|'+fnum.toString()+'|'+fnum1.toString()+'|'+fnum2.toString()+'|'+ fmnum.toString()+'|'+fmnum1.toString()+'|'+fmnum2.toString();

        return result;
    }
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count3")
    @ResponseBody
    public String Groupwedding(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer wedding1=0;//结婚
        Integer wedding2=0;//未婚
        Integer wedding3=0;//离异
        Integer wedding4=0;//丧偶
        Integer other=0;//其他
        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("已婚".equals(list.get(i).getMarital())) {
                    wedding1++;
                }else if("未婚".equals(list.get(i).getMarital())) {
                    wedding2++;
                }else if("离婚".equals(list.get(i).getMarital())){
                    wedding3++;
                }else if("丧偶".equals(list.get(i).getMarital())){
                    wedding4++;
                }else{
                    other++;
                }
            }}
        String result=count1.toString()+'|'+wedding1.toString()+'|'+wedding2.toString()+'|'+wedding3.toString()+'|'+ wedding4.toString()+'|'+other.toString();

        return result;
    }
    //统计村民的政治面貌
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count4")
    @ResponseBody
    public String Grouppol(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer pol1=0;//群众
        Integer pol2=0;//中共党员
        Integer pol3=0;//中共预备党员
        Integer pol4=0;//共青团员
        Integer other=0;//其它
        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("群众".equals(list.get(i).getPolitics())) {
                    pol1++;
                }else if("中共党员".equals(list.get(i).getPolitics())){
                    pol2++;
                }else if("中共预备党员".equals(list.get(i).getPolitics())){
                    pol3++;
                }else if("共青团员".equals(list.get(i).getPolitics())){
                    pol4++;
                }else {
                    other++;
                }

            }}
        String result=count1.toString()+'|'+pol1.toString()+'|'+ pol2.toString()+'|'+pol3.toString()+'|'+pol4.toString()+'|'+other.toString();

        return result;
    }
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //年龄情况统计
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count5")
    @ResponseBody
    public String Groupage(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer  age[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        String idcardnum="";
        /*if(getAgeByIDNumber(idcardnum)>60){
                agenum1++;
            }
            else if(getAgeByIDNumber(idcardnum)<10){
                agenum2++;
            }
            else if(getAgeByIDNumber(idcardnum)>10&&getAgeByIDNumber(idcardnum)<60){
                agenum3++;
            }*/
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("男".equals(list.get(i).getSex())) {
                    if(getAgeByIDNumber(idcardnum)>=0&&getAgeByIDNumber(idcardnum)<=10){
                        age[1]++;
                    }
                    else if(getAgeByIDNumber(idcardnum)<=20){
                        age[2]++;
                    }
                    else if(getAgeByIDNumber(idcardnum)<=30){
                        age[3]++;
                    }else if(getAgeByIDNumber(idcardnum)<=40){
                        age[4]++;
                    }else if(getAgeByIDNumber(idcardnum)<=50){
                        age[5]++;
                    }else if(getAgeByIDNumber(idcardnum)<=60){
                        age[6]++;
                    }else if(getAgeByIDNumber(idcardnum)<=70){
                        age[7]++;
                    }else{
                        age[8]++;
                    }

                }else{
                    if(getAgeByIDNumber(idcardnum)>=0&&getAgeByIDNumber(idcardnum)<=10){
                        age[10]++;
                    }
                    else if(getAgeByIDNumber(idcardnum)<=20){
                        age[11]++;
                    }
                    else if(getAgeByIDNumber(idcardnum)<=30){
                        age[12]++;
                    }else if(getAgeByIDNumber(idcardnum)<=40){
                        age[13]++;
                    }else if(getAgeByIDNumber(idcardnum)<=50){
                        age[14]++;
                    }else if(getAgeByIDNumber(idcardnum)<=60){
                        age[15]++;
                    }else if(getAgeByIDNumber(idcardnum)<=70){
                        age[16]++;
                    }else{
                        age[17]++;
                    }

                }
            }}

        String result=count1.toString()+'|'+age[0].toString()+'|'+age[1].toString()+'|'+age[2].toString()+'|'+ age[3].toString()+'|'+age[4].toString()+'|'+age[5].toString()+'|'+age[6].toString()+'|'+age[7].toString()+'|'+age[8].toString()+'|'+age[9].toString()+'|'+ age[10].toString()+'|'+age[11].toString()+'|'+age[12].toString()+'|'+age[13].toString()+'|'+age[14].toString()+'|'+age[15].toString()+'|'+age[16].toString()+'|'+ age[17].toString();



        return result;
    }
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count6")
    @ResponseBody
    public String Grouphouse(String aid,String startTime,String endTime)
    {

        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer count1=0;
        Integer house1=0;
        Integer house2=0;
        Integer house3=0;
        Integer house4=0;
        Integer house5=0;
        Integer house6=0;
        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("男".equals(list.get(i).getSex())) {
                    if("自建房".equals(list.get(i).getHouse())){
                        house1++;
                    }else if("租用房".equals(list.get(i).getHouse())){
                        house2++;
                    }else{
                        house3++;
                    }
                }else{
                    if("自建房".equals(list.get(i).getHouse())){
                        house4++;
                    }else if("租用房".equals(list.get(i).getHouse())){
                        house5++;
                    }else{
                        house6++;
                    }
                }

            }}
        String result=count1.toString()+'|'+house1.toString()+'|'+ house2.toString()+'|'+house3.toString()+'|'+ house4.toString()+'|'+house5.toString()+'|'+ house6.toString();

        return result;
    }
    //统计是否为复健军人
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count7")
    @ResponseBody
    public String Groupismodeb(String aid,String startTime,String endTime)
    {
        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count1=0;
        Integer count=list.size();//总数
        Integer isdemob0=0;//复健军人
        Integer isdemob1=0;//不是复健军人

        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if(list.get(i).getIsdemob()==1){
                    isdemob0++;
                }else {
                    isdemob1++;
                }
            }}
        String result=count1.toString()+'|'+isdemob0.toString()+'|'+ isdemob1.toString();

        return result;
    }
    //统计是否为低保户
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count8")
    @ResponseBody
    public String Groupislowincome(String aid,String startTime,String endTime)
    {
        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count=list.size();//总数
        Integer islowincome0=0;//为低保户
        Integer islowincome1=0;//不是低保户
        Integer count1=0;

        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if(list.get(i).getIslowincome()==1){
                    islowincome0++;
                }else {
                    islowincome1++;
                }
            }}
        String result=count1.toString()+'|'+islowincome0.toString()+'|'+ islowincome1.toString();

        return result;
    }
    //统计家庭基础设施状况
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count9")
    @ResponseBody
    public String Groupisfacilities(String aid,String startTime,String endTime)
    {
        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count1=0;
        Integer count=list.size();//总数
        Integer psa1=0;//是否接入电力
        Integer psa0=0;//否
        Integer ca1=0;//是否接入有线电视
        Integer ca0=0;//否
        Integer woban1=0;//是否接入宽带
        Integer woban0=0;//否
        Integer water1=0;//是否接入自来水
        Integer water0=0;//否
        Integer ssa1=0;//是否接入安防系统
        Integer ssa0=0;//否
        Integer gfs1=0;//是否接入燃气
        Integer gfs0=0;//否
        Integer wdr1=0;//是否垃圾分类
        Integer wdr0=0;//否
        Integer rth1=0;//是否道路到户
        Integer rth0=0;//否

        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if(list.get(i).getPsa()==1){
                    psa1++;
                }else {
                    psa0++;
                }
                if(list.get(i).getCa()==1){
                    ca1++;
                }else {
                    ca0++;
                }
                if(list.get(i).getWoban()==1){
                    woban1++;
                }else {
                    woban0++;
                }
                if(list.get(i).getWater()==1){
                    water1++;
                }else {
                    water0++;
                }
                if(list.get(i).getSsa()==1){
                    ssa1++;
                }else {
                    ssa0++;
                }
                if(list.get(i).getGfs()==1){
                    gfs1++;
                }else {
                    gfs0++;
                }
                if(list.get(i).getWdr()==1){
                    wdr1++;
                }else {
                    wdr0++;
                }
                if(list.get(i).getRth()==1){
                    rth1++;
                }else {
                    rth0++;
                }

            }}
        String result=count1.toString()+'|' +psa1.toString()+'|'+ psa0.toString() +'|'+ca1.toString()+'|'+ca0.toString() +'|'+woban1.toString()+'|'+woban0.toString() +'|'+water1.toString()+'|'+water0.toString() +'|'+ssa1.toString()+'|'+ssa0.toString() +'|'+gfs1.toString()+'|'+gfs0.toString() +'|'+wdr1.toString()+'|'+wdr0.toString() +'|'+rth1.toString()+'|'+rth0.toString();

        return result;
    }


    //统计就业状态
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count10")
    @ResponseBody
    public String Groupisjobstate(String aid,String startTime,String endTime)
    {
        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count1=0;
        Integer count=list.size();//总数
        Integer jobstate1=0;//在业
        Integer jobstate0=0;//失业

        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("在业".equals(list.get(i).getJobstate())){
                    jobstate1++;
                }else {
                    jobstate0++;
                }
            }}
        String result=count.toString()+'|'+jobstate1.toString()+'|'+ jobstate0.toString();

        return result;
    }

    //统计职业
    @RequiresPermissions("village:analysis:list")
    @PostMapping("/count11")
    @ResponseBody
    public String Groupisjob(String aid,String startTime,String endTime)
    {
        List<VillagerInfo> list = villagerInfoService.selectVillagerInfoListBygrouptype(aid);
        Integer count1=0;
        Integer count=list.size();//总数
        Integer job0=0;//保洁员
        Integer job1=0;//     油漆工
        Integer job2=0;//保险，保险员
        Integer job3=0;//    厨师
        Integer job4=0;//  泥工
        Integer job5=0;//       销售
        Integer job6=0;//环保
        Integer job7=0;//       木工
        Integer job8=0;//  妇女组长，妇女队长
        Integer job9=0;//   教师
        Integer job10=0;//  水电工
        Integer job11=0;//农民
        Integer job12=0;//其他

        String idcardnum="";
        for(int i=0;i<count;i++){
            idcardnum=list.get(i).getIdcard();
            String date2 = extractYearMonthDayOfIdCard(idcardnum);
            if (startTime.compareTo(date2) <= 0&&endTime.compareTo(date2) >=0) {
                count1++;
                if("保洁员".equals(list.get(i).getJob())){
                    job0++;
                }else if("油漆工".equals(list.get(i).getJob())){
                    job1++;
                } else if("保险".equals(list.get(i).getJob())||"保险员".equals(list.get(i).getJob())){
                    job2++;
                }else if("厨师".equals(list.get(i).getJob())){
                    job3++;
                }else if("泥工".equals(list.get(i).getJob())){
                    job4++;
                }else if("销售".equals(list.get(i).getJob())){
                    job5++;
                }else if("环保".equals(list.get(i).getJob())){
                    job6++;
                }else if("木工".equals(list.get(i).getJob())){
                    job7++;
                }else if("妇女组长".equals(list.get(i).getJob())||"妇女队长".equals(list.get(i).getJob())){
                    job8++;
                }else if("教师".equals(list.get(i).getJob())){
                    job9++;
                }else if("水电工".equals(list.get(i).getJob())){
                    job10++;
                }else if("农民".equals(list.get(i).getJob())){
                    job11++;
                }else{
                    job12++;
                }
            }}
        String result=count1.toString()+'|'+job0.toString()+'|'+ job1.toString()
                +'|'+ job2.toString()+'|'+ job3.toString()+'|'+ job4.toString()
                +'|'+ job5.toString()+'|'+ job6.toString()+'|'+ job7.toString()
                +'|'+ job8.toString()+'|'+ job9.toString()+'|'+ job10.toString()
                +'|'+ job11.toString()+'|'+ job12.toString();

        return result;
    }


}
