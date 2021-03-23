package com.example.lifedemo01.AccountBook;

import com.example.lifedemo01.AccountBook.Trading.entity.CoTrading;
import com.example.lifedemo01.AccountBook.Trading.service.CoTradingService;
import com.example.lifedemo01.Pub.EnumTool;
import com.example.lifedemo01.Pub.SnowFlake;
import com.example.lifedemo01.System.entity.SysUser;
import com.example.lifedemo01.System.service.SysUserService;
import com.example.lifedemo01.common.DateTool;
import com.example.lifedemo01.common.MapTool;
import com.example.lifedemo01.common.util.ReadExcle;
import com.example.lifedemo01.entity.BaseDict;
import com.example.lifedemo01.service.BaseDictService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

/**
 * 账本业务类
 *
 * @Author: RenHe
 * @Date: 2021/2/5 14:27
 */
@Slf4j
@Component
public class CalculateCmd {
    @Resource
    private CoTradingService coTradingService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private BaseDictService baseDictService;

//    BaseDict eatDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "0");
//    BaseDict clothDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "1");
//    BaseDict useDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "2");
//    BaseDict playDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "3");
//    BaseDict liveDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "4");
//    BaseDict investDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "5");
//    BaseDict gifDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "6");
//    BaseDict trafficDict = this.baseDictService.queryById("CO_SPENDING_CONTENT", "7");
//    List<String> eatList = Arrays.asList(eatDict.getDictValue().split(" "));
//    List<String> clothList = Arrays.asList(clothDict.getDictValue().split(" "));
//    List<String> useList = Arrays.asList(useDict.getDictValue().split(" "));
//    List<String> playList = Arrays.asList(playDict.getDictValue().split(" "));
//    List<String> liveList = Arrays.asList(liveDict.getDictValue().split(" "));
//    List<String> investList = Arrays.asList(investDict.getDictValue().split(" "));
//    List<String> gifList = Arrays.asList(gifDict.getDictValue().split(" "));
//    List<String> trafficList = Arrays.asList(trafficDict.getDictValue().split(" "));

    /**
     * 导入支付宝EXCLE账单到数据库
     *
     * @return
     */
    public List<Map<String, Object>> importAccount() {
        String filePath = "C:\\Users\\Administrator\\Desktop\\alipay_record_20210205_1501_1.xlsx";
        int beginLine = 5;
        try {
            // 读取Excle文件转化为List<List<String>>
            List<List<String>> parseList = new ReadExcle().readExcel(filePath, beginLine);
            if (null != parseList) {
                Map<String, String> ioMap = EnumTool.getEmunMapByDictId("CO_TRADING_IO");
                Map<String, String> payStatusMap = EnumTool.getEmunMapByDictId("CO_PAY_STATUS");
                Map<String, String> headMap = new ReadExcle().readExcelHead(filePath);
                List<CoTrading> resultLine = new ArrayList<CoTrading>();
                SnowFlake snowFlake = new SnowFlake(0, 0);
                BaseDict baseDict = new BaseDict();
                baseDict.setDictId("CO_SPENDING_CONTENT");
                List<BaseDict> spendKindList = this.baseDictService.queryAll(baseDict);
                // 读取汇总信息-头表
                // 消费者姓名
                String custName = headMap.get("custName");
                // 消费者账户
                String custTel = headMap.get("custTel");
                // 开始日期
                String beginDate = headMap.get("beginDate");
                // 结束日期
                String endDate = headMap.get("endDate");
                // 共计收入
                String sumIncome = headMap.get("sumIncome");
                // 共计支出
                String sumSpending = headMap.get("sumSpending");
                // 插入时间 yyyyMMddHHmmss
                String crtTime = DateTool.getCurrentTimeMillisAsString();
                // 消费者Id
                SysUser user = new SysUser();
                user.setUserName(custName);
                List<SysUser> userList = sysUserService.queryAll(user);
                String custNo = ((null != userList && userList.size() > 0) ? userList.get(0).getUserId() : "1024");

                // 读取详细信息-行表
                for (List<String> list : parseList) {
                    CoTrading trading = new CoTrading();
                    // 支付编号
                    String payNo = snowFlake.nextId();
                    // 支付时间
                    String payTime = list.get(2).trim();
                    // 收款方名称
                    String payeeName = list.get(7).trim();
                    // 商品
                    String commodityName = list.get(8).trim();
                    // 金额
                    String amount = list.get(9).trim();
                    // 收入/支出
                    String io = list.get(10).trim();
                    io = (ioMap.containsValue(io) ? (String) MapTool.getKey(ioMap, io) : io);
                    // 支付状态
                    String payStatus = list.get(11).trim();
                    payStatus = (payStatusMap.containsValue(payStatus) ? (String) MapTool.getKey(payStatusMap, payStatus) : payStatus);
                    // 消费分类
                    String spendingKind = "000";

                    // 收入分类
                    String incomeKind = "1";
                    // 备注
                    String note = list.get(14).trim();
                    // 放值
                    trading.setPayNo(payNo);
                    trading.setCustNo(custNo);
                    trading.setPayTime(payTime);
                    trading.setPayeeName(payeeName);
                    trading.setCommodityName(commodityName);
                    trading.setAmount(amount);
                    trading.setIo(io);
                    trading.setPayStatus(payStatus);
                    trading.setSpendingKind(spendingKind);
                    trading.setIncomeKind(incomeKind);
                    trading.setNote(note);
                    trading.setIsEffect("0");
                    trading.setPayLable("1");
                    trading = changeType(trading,spendKindList);
                    resultLine.add(trading);
                }
                int count = 0, seccessCount = 0, errorCount = 0;
                StringBuffer sb = new StringBuffer();
                String errorMsg = "";
                sb = sb.append("编号");
                // 插入数据库
                if (null != resultLine) {
                    for (CoTrading coTrading : resultLine) {
                        count++;
                        try {
                            this.coTradingService.insert(coTrading);
                            seccessCount++;
                            log.debug("插入成功");
                        } catch (Exception e) {
                            errorCount++;
                            log.debug("error:" + e);
                            sb = sb.append(coTrading.getPayNo() + "，");
                        }
                    }
                }
                sb = sb.append("插入失败");
                log.debug("共导入" + count + "条，其中，插入成功" + seccessCount + "条，插入失败" + errorCount + "条");
                if (errorCount != 0) {
                    log.debug(sb.toString());
                    errorMsg = sb.toString();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param
     */
    public CoTrading changeType(CoTrading coTrading,List<BaseDict> spendKindList) {
        String payeeName = coTrading.getPayeeName();
        String commodityName = coTrading.getCommodityName();
        String note = coTrading.getNote();
        String io = coTrading.getIo();
        if ("0".equals(io)) {
            boolean hasKind = false;
            if (null != spendKindList && spendKindList.size() > 0) {
                for (BaseDict bd : spendKindList) {
                    List<String> list = Arrays.asList(bd.getDictValue().split(" "));
                    for (String str : list) {
                        if (payeeName.contains(str) || commodityName.contains(str) || note.contains(str)) {
                            // 这里，数据字典中的CO_SPENDING_CONTENT与CO_SPENDING_KIND的key应该一致，手动维护添加支出分类时应设置为一并添加
                            coTrading.setSpendingKind(bd.getDictKey());
                            coTrading.setIsEffect("1");
                            hasKind = true;
                            break;
                        }
                    }
                    if (hasKind) {
                        log.debug("已分类");
                        break;
                    }
                }
            }
        } else if (io.equals("1")) {
            log.debug("收入暂不做处理");
        }
        return coTrading;
    }


    public static void main(String[] args) {
        CalculateCmd calculateCmd = new CalculateCmd();
        calculateCmd.importAccount();
    }
}

