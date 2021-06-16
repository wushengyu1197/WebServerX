package com.chiyu.WebContorller;

import com.alibaba.fastjson.JSONObject;
import com.chiyu.Util.CommonUtils;
import com.chiyu.Util.HttpRequestUtil;
import com.chiyu.domin.DorLog;
import com.chiyu.domin.DorLogVO;
import com.chiyu.domin.LTreq;
import com.chiyu.service.DorLogService;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DorLogContorller {

    private Logger log = LoggerFactory.getLogger(DorLogContorller.class);

    @Autowired
    DorLogService dorLogService;

    static List<DorLog> dorLogsList = new ArrayList<>();
    /**
     * 注册和人流量服务
     * @return
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    // @Async
    public List<DorLog> app() throws Exception {
        //获取token
        String token = getToken();
        //注册
        //String zcurl = "";//注册接口
        String rllurl = "http://58.33.1.5:4aps/mjflow/MJ";//人流量日志接口
        List<DorLog> dorLogs = dorLogService.listDorLog();
        //SimpleDateFormat utcSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int in = 0;
        int out = 0;
        //新数据>旧数据
        if (dorLogs.size() > dorLogsList.size()) {
            //取最后的数据
            for (int i = dorLogsList.size(); i < dorLogs.size(); i++) {
                DorLog dorLog = dorLogs.get(i);
                log.info("遍历第:{}新数据",i);

                /*if (dorLog.getJiajiid() == 04) {
                    zcurl = "http://58.3.104.02/api/devices/MJ04";
                }
                if (dorLog.getJiajiid() == 03) {
                    zcurl = "http://58.33.0002/api/devices/MJ03";
                }*/

                if ("入馆".equals(dorLog.getInOutIndication())) {
                    in = 1;
                    out = 0;
                } else {
                    //出馆
                    in = 0;
                    out = 1;
                }
                //数据封装
                /* Map<String, Object> map = new LinkedHashMap<>();
                Map<String, Object> parts = new LinkedHashMap<>();
                List<Map<String, Object>> list = new ArrayList<>();

                parts.put("id", "string");
                parts.put("name", "闸机");
                parts.put("status", 0);
                parts.put("description", "状态描述");
                list.add(parts);

                map.put("category_id", 10);
                map.put("hardware_version", "string");
                map.put("software_version", "string");
                map.put("ip_address", "string");
                map.put("parts", list);

                String param = JSONObject.toJSONString(map);
                //发送HTTP请求
                String retult = HttpRequestUtil.sendPost(zcurl, param, token);
                log.info("注册返回结果:" + retult);
*/

                /*******************************人流量**********************************************/
                Map<String, Object> pamar = new LinkedHashMap<>();
                pamar.put("gate_no", dorLog.getTerminalID());
                pamar.put("gate_name", dorLog.getDoorNo());
                pamar.put("in", in);
                pamar.put("out", out);
                //转成yyyy-MM-dd'T'HH:mm:ss.SSSXXX
                pamar.put("datetime", CommonUtils.getISO8601TimestampFromDateStr(dorLog.getDateTime()));
                String jsonString = JSONObject.toJSONString(pamar);

                //发送HTTP请求
                String retult2 = HttpRequestUtil.sendPost(rllurl, jsonString, token);
                log.info("人流量返回结果:" + retult2);

                DorLogVO dorLogVO=new DorLogVO();
                dorLogVO.setDatetime(CommonUtils.getISO8601TimestampFromDateStr(dorLog.getDateTime()));
                dorLogVO.setGate_name(dorLog.getDoorNo().toString());
                dorLogVO.setGate_no(dorLog.getTerminalID().toString());
                dorLogVO.setIn(String.valueOf(in));
                dorLogVO.setOut(String.valueOf(out));

                //1. 电子报运（1）远程访问海关报运平台录入报运单、商品信息
                Response post = WebClient.create("http://58.58:02/api/logs/mjflow/MJ").post(dorLogVO);
                log.info("返回结果:{}",post.getStatus());
                //2. 电子报运（2）根据报运单id，查询报运结果
                //ExportResult exportResult = WebClient.create("http://localhost:8989/ws/export/user/" + id).get(ExportResult.class);

                //存放旧数据
                dorLogsList = dorLogs;
            }

        }


        return dorLogs;
    }

    //注册,人流量接口获取token方法
    public String getToken() {
        String url = "http://58.33.102/api/auth/token";
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        LTreq lTreq = new LTreq();
        lTreq.setUsername("shrfid");
        lTreq.setPassword("111");
        String param = JSONObject.toJSONString(lTreq);
        String retult = HttpRequestUtil.sendPost2(url, param);
        log.info("retult返回结果:" + retult);
        JSONObject jsonObject = JSONObject.parseObject(retult);
        Object access_token = jsonObject.get("token");
        return access_token.toString();
    }

}

