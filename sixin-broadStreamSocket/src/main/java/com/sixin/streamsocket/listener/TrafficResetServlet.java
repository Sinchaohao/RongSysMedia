//package com.sixin.streamsocket.listener;
//
//import com.sixin.broad.utils.bConst;
//import com.sixin.broad.utils.bTools;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author 张超 teavamc
// * @Description:TODO
// * @ClassName TrafficResetServlet
// * @date 2019/2/19 14:01
// **/
//public class TrafficResetServlet  extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    public void init(){
////		String qztime = "0 * * * * ?";
//        String date = bTools.readTxtFile(bConst.TRAFFICRESEDATE);
//        String qztime = "0 0 0 "+Integer.parseInt(date)+" * ?";
//        if(QuartzManager.checkExists(bConst.TRAFFICRESEJOB)){
//            QuartzManager.modifyJobTime(bConst.TRAFFICRESEJOB, qztime);
//        }else{
//            QuartzManager.addJob(bConst.TRAFFICRESEJOB, TrafficQuartzJob.class, qztime );
//        }
//    }
//
//    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
//            throws ServletException, IOException {
//    }
//}
