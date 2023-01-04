package com.base;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串截取
 *
 * @author djq
 * @date 2021/8/30 16:40
 */
public class 字符串截取 {
    public static void main(String[] args) {
        String a = 竖杠切割();
//        String b = test();
    }

    private static String test() {
        String sss = "[\"SELECT KHXM,XB,ZJLB,KHTYBH,ZJHM,KHLX,GJ,MZ,XL,CSRQ,ZY,ZC,SFNH,JTDH,YDDH,GRYSR,JTYSR,HYQK,BXYGBZ,GZDWMC,GZDWDZ,GZDWDH,DWXZ,JTZZ,TXDZ,CJRQ FROM default.ffjz_node_person_e_1017487509250252800 BULK UPSERT (:个人{KHXM:KHXM,XB:XB,ZJLB:ZJLB,KHTYBH:KHTYBH,__uid:ZJHM,KHLX:KHLX,GJ:GJ,MZ:MZ,XL:XL,CSRQ:CSRQ,ZY:ZY,ZC:ZC,SFNH:SFNH,JTDH:JTDH,YDDH:YDDH,GRYSR:GRYSR,JTYSR:JTYSR,HYQK:HYQK,BXYGBZ:BXYGBZ,GZDWMC:GZDWMC,GZDWDZ:GZDWDZ,GZDWDH:GZDWDH,DWXZ:DWXZ,JTZZ:JTZZ,TXDZ:TXDZ,CJRQ:CJRQ})\",\"SELECT KHMC,KHLX,TYSHXYDM,ZCZB,ZCZBBZ,SSZB,SSZBBZ,CLRQ,Industry,QYFL,SSGSBZ,XYPJ,YGRS,FXYJXH,BZ,CJRQ FROM default.ffjz_node_enterprise_e_1017487516250546176 BULK UPSERT (:企业{KHMC:KHMC,KHLX:KHLX,__uid:TYSHXYDM,ZCZB:ZCZB,ZCZBBZ:ZCZBBZ,SSZB:SSZB,SSZBBZ:SSZBBZ,CLRQ:CLRQ,Industry:Industry,QYFL:QYFL,SSGSBZ:SSGSBZ,XYPJ:XYPJ,YGRS:YGRS,FXYJXH:FXYJXH,BZ:BZ,CJRQ:CJRQ})\",\"SELECT UUID_TELE,TELE,UPDATE_TIME FROM default.ffjz_node_tele_e_1017487521061412864 BULK UPSERT (:电话{__uid:UUID_TELE,TELE:TELE,UPDATE_TIME:UPDATE_TIME})\",\"SELECT UUID_ADDR,ADDRESS,update_time FROM default.ffjz_node_address_e_1017487522072240128 BULK UPSERT (:地址{__uid:UUID_ADDR,ADDRESS:ADDRESS,update_time:update_time})\",\"SELECT YHJGMC,ZHMC,HQCKZH,GRHQCKZHLX,BZ,CKYE,KHRQ,SCDHRQ,CHLB,XHRQ,ZHZT FROM default.ffjz_node_enterprise_acc_e_1017487523150176256 BULK UPSERT (:对公账号{YHJGMC:YHJGMC,ZHMC:ZHMC,__uid:HQCKZH,GRHQCKZHLX:GRHQCKZHLX,BZ:BZ,CKYE:CKYE,KHRQ:KHRQ,SCDHRQ:SCDHRQ,CHLB:CHLB,XHRQ:XHRQ,ZHZT:ZHZT})\",\"SELECT YHJGMC,ZHMC,HQCKZH,GRHQCKZHLX,KH,BZ,CKYE,KHRQ,SCDHRQ,CHLB,XHRQ,ZHZT FROM default.ffjz_node_personal_acc_e_1017487526350430208 BULK UPSERT (:个人账号{YHJGMC:YHJGMC,ZHMC:ZHMC,__uid:HQCKZH,GRHQCKZHLX:GRHQCKZHLX,KH:KH,BZ:BZ,CKYE:CKYE,KHRQ:KHRQ,SCDHRQ:SCDHRQ,CHLB:CHLB,XHRQ:XHRQ,ZHZT:ZHZT})\",\"SELECT UUID_IP,IP_ADDRESS FROM default.ffjz_node_ip_e_1017487529823313920 BULK UPSERT (:IP{__uid:UUID_IP,IP_ADDRESS:IP_ADDRESS})\",\"SELECT MAC,UUID_MAC FROM default.ffjz_node_mac_e_1017487530624425984 BULK UPSERT (:MAC{MAC:MAC,__uid:UUID_MAC})\",\"SELECT UUID,ZJHM,TYSHXYDM FROM default.ffjz_edge_person_to_ent_finance_n_1017487531589115904 BULK UPSERT [:财务人员{__udid:UUID,__uslabels:['财务人员'],__udlabels:['财务人员'],__usid:UUID,ZJHM:ZJHM,TYSHXYDM:TYSHXYDM}]\",\"SELECT UUID,ZJHM,DHLB,UUID_TELE FROM default.ffjz_edge_person_to_tele_n_1017487533468164096 BULK UPSERT [:移动电话/家庭电话{__udid:UUID,__uslabels:['移动电话/家庭电话'],__udlabels:['移动电话/家庭电话'],__usid:UUID,ZJHM:ZJHM,DHLB:DHLB,UUID_TELE:UUID_TELE}]\",\"SELECT UUID,TYSHXYDM,DHLB,UUID_TELE FROM default.ffjz_edge_enterprise_to_tele_n_1017487534864867328 BULK UPSERT [:联系电话{__udid:UUID,__uslabels:['联系电话'],__udlabels:['联系电话'],__usid:UUID,TYSHXYDM:TYSHXYDM,DHLB:DHLB,UUID_TELE:UUID_TELE}]\",\"SELECT UUID,ZJHM,UUID_JTZZ FROM default.ffjz_edge_person_to_live_address_n_1017487536269959168 BULK UPSERT [:家庭地址{__udid:UUID,__uslabels:['家庭地址'],__udlabels:['家庭地址'],__usid:UUID,ZJHM:ZJHM,UUID_JTZZ:UUID_JTZZ}]\",\"SELECT UUID,TYSHXYDM,UUID_ZCDZ FROM default.ffjz_edge_enterprise_register_address_n_1017487537196900352 BULK UPSERT [:注册地址{__udid:UUID,__uslabels:['注册地址'],__udlabels:['注册地址'],__usid:UUID,TYSHXYDM:TYSHXYDM,UUID_ZCDZ:UUID_ZCDZ}]\",\"SELECT UUID,HQCKZH1,HQCKZH2,LJJYJE,LJJYCS,PJJYJE,ZZYCJYRQ,ZWYCJYRQ,ZXJYJE,ZDJYJE FROM default.ffjz_edge_person_to_ent_transation_n_1017487538270642176 BULK UPSERT [:交易{__udid:UUID,__uslabels:['交易'],__udlabels:['交易'],__usid:UUID,HQCKZH1:HQCKZH1,HQCKZH2:HQCKZH2,LJJYJE:LJJYJE,LJJYCS:LJJYCS,PJJYJE:PJJYJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ,ZXJYJE:ZXJYJE,ZDJYJE:ZDJYJE}]\",\"SELECT UUID,GRHQCKZH,UUID_IP,LJZCJE,LJZCCS,PJZCJE,ZZYCJYRQ,ZWYCJYRQ FROM default.ffjz_edge_person_acc_to_ip_n_1017487541034688512 BULK UPSERT [:个人交易IP地址{__udid:UUID,__uslabels:['个人交易IP地址'],__udlabels:['个人交易IP地址'],__usid:UUID,GRHQCKZH:GRHQCKZH,UUID_IP:UUID_IP,LJZCJE:LJZCJE,LJZCCS:LJZCCS,PJZCJE:PJZCJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ}]\",\"SELECT UUID,GRHQCKZH,UUID_MAC,LJZCJE,PJZCJE,LJZCCS,ZZYCJYRQ,ZWYCJYRQ FROM default.ffjz_edge_enterprise_acc_to_mac_n_1017487543396081664 BULK UPSERT [:对公交易MAC地址{__udid:UUID,__uslabels:['对公交易MAC地址'],__udlabels:['对公交易MAC地址'],__usid:UUID,GRHQCKZH:GRHQCKZH,UUID_MAC:UUID_MAC,LJZCJE:LJZCJE,PJZCJE:PJZCJE,LJZCCS:LJZCCS,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ}]\",\"SELECT UUID,ZJHM1,ZJHM2,GXLX FROM default.ffjz_edge_person_to_person_relation_n_1017487545795223552 BULK UPSERT [:社会关系{__udid:UUID,__uslabels:['社会关系'],__udlabels:['社会关系'],__usid:UUID,ZJHM1:ZJHM1,ZJHM2:ZJHM2,GXLX:GXLX}]\",\"SELECT UUID,TYSHXYDM_CHILD,TYSHXYDM_MOTHER FROM default.ffjz_edge_enterprise_to_ent_child_n_1017487547116429312 BULK UPSERT [:母公司{__udid:UUID,__uslabels:['母公司'],__udlabels:['母公司'],__usid:UUID,TYSHXYDM_CHILD:TYSHXYDM_CHILD,TYSHXYDM_MOTHER:TYSHXYDM_MOTHER}]\",\"SELECT UUID,TYSHXYDM1,TYSHXYDM2,GXLB FROM default.ffjz_edge_enterprise_to_ent_other_relation_n_1017487548274057216 BULK UPSERT [:关联人企企{__udid:UUID,__uslabels:['关联人企企'],__udlabels:['关联人企企'],__usid:UUID,TYSHXYDM1:TYSHXYDM1,TYSHXYDM2:TYSHXYDM2,GXLB:GXLB}]\",\"SELECT UUID,ZJHM,TYSHXYDM FROM default.ffjz_edge_person_to_ent_control_n_1017487550106968064 BULK UPSERT [:实控人{__udid:UUID,__uslabels:['实控人'],__udlabels:['实控人'],__usid:UUID,ZJHM:ZJHM,TYSHXYDM:TYSHXYDM}]\",\"SELECT UUID,ZJHM,TYSHXYDM,GXLB FROM default.ffjz_edge_person_to_ent_other_relation_n_1017487551004549120 BULK UPSERT [:关联人企人{__udid:UUID,__uslabels:['关联人企人'],__udlabels:['关联人企人'],__usid:UUID,ZJHM:ZJHM,TYSHXYDM:TYSHXYDM,GXLB:GXLB}]\",\"SELECT UUID,ZJHM,TYSHXYDM FROM default.ffjz_edge_person_to_ent_legal_n_1017487552397058048 BULK UPSERT [:法人代表{__udid:UUID,__uslabels:['法人代表'],__udlabels:['法人代表'],__usid:UUID,ZJHM:ZJHM,TYSHXYDM:TYSHXYDM}]\",\"SELECT UUID,ZJHM,GZDWDZ,UUID_ADDR FROM default.ffjz_edge_person_to_work_address_n_1017487553533714432 BULK UPSERT [:工作地址{__udid:UUID,__uslabels:['工作地址'],__udlabels:['工作地址'],__usid:UUID,ZJHM:ZJHM,GZDWDZ:GZDWDZ,UUID_ADDR:UUID_ADDR}]\",\"SELECT uuid,ZHMC,TYSHXYDM,HQCKZH,YHJGMC,KHRQ,YHJGDM FROM default.ffjz_edge_enterprise_to_acc_n_1017487554846531584 BULK UPSERT [:开立对公账户{__udid:uuid,__uslabels:['开立对公账户'],__udlabels:['开立对公账户'],__usid:uuid,ZHMC:ZHMC,TYSHXYDM:TYSHXYDM,HQCKZH:HQCKZH,YHJGMC:YHJGMC,KHRQ:KHRQ,YHJGDM:YHJGDM}]\",\"SELECT UUID,GRHQCKZH,UUID_MAC,LJZCJE,LJZCCS,PJZCJE,ZZYCJYRQ,ZWYCJYRQ FROM default.ffjz_edge_person_acc_to_mac_n_1017487557694464000 BULK UPSERT [:个人交易MAC地址{__udid:UUID,__uslabels:['个人交易MAC地址'],__udlabels:['个人交易MAC地址'],__usid:UUID,GRHQCKZH:GRHQCKZH,UUID_MAC:UUID_MAC,LJZCJE:LJZCJE,LJZCCS:LJZCCS,PJZCJE:PJZCJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ}]\",\"SELECT UUID,HQCKZH,UUID_IP,LJZCJE,LJZCCS,PJZCJE,ZZYCJYRQ,ZWYCJYRQ FROM default.ffjz_edge_enterprise_acc_to_ip_n_1017487561016352768 BULK UPSERT [:对公交易IP地址{__udid:UUID,__uslabels:['对公交易IP地址'],__udlabels:['对公交易IP地址'],__usid:UUID,HQCKZH:HQCKZH,UUID_IP:UUID_IP,LJZCJE:LJZCJE,LJZCCS:LJZCCS,PJZCJE:PJZCJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ}]\",\"SELECT UUID,HQCKZH1,HQCKZH2,LJJYJE,LJJYCS,PJJYJE,ZZYCJYRQ,ZWYCJYRQ,ZXJYJE,ZDJYJE FROM default.ffjz_edge_enterprise_transation_n_1017487563532935168 BULK UPSERT [:企业间转账{__udid:UUID,__uslabels:['企业间转账'],__udlabels:['企业间转账'],__usid:UUID,HQCKZH1:HQCKZH1,HQCKZH2:HQCKZH2,LJJYJE:LJJYJE,LJJYCS:LJJYCS,PJJYJE:PJJYJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ,ZXJYJE:ZXJYJE,ZDJYJE:ZDJYJE}]\",\"SELECT UUID,TYSHXYDM1,TYSHXYDM2,GXLB FROM default.ffjz_edge_enterprise_to_ent_other_relation_n_1017487566607360000 BULK UPSERT [:企业间其他关系{__udid:UUID,__uslabels:['企业间其他关系'],__udlabels:['企业间其他关系'],__usid:UUID,TYSHXYDM1:TYSHXYDM1,TYSHXYDM2:TYSHXYDM2,GXLB:GXLB}]\",\"SELECT UUID,HQCKZH1,HQCKZH2,LJJYJE,LJJYCS,PJJYJE,ZZYCJYRQ,ZWYCJYRQ,ZXJYJE,ZDJYJE FROM default.ffjz_edge_person_to_person_transation_n_1017487568004063232 BULK UPSERT [:个人间转账{__udid:UUID,__uslabels:['个人间转账'],__udlabels:['个人间转账'],__usid:UUID,HQCKZH1:HQCKZH1,HQCKZH2:HQCKZH2,LJJYJE:LJJYJE,LJJYCS:LJJYCS,PJJYJE:PJJYJE,ZZYCJYRQ:ZZYCJYRQ,ZWYCJYRQ:ZWYCJYRQ,ZXJYJE:ZXJYJE,ZDJYJE:ZDJYJE}]\"]";
        List<String> ss = JSONObject.parseArray(sss,String.class);
        ss.forEach(o->{
//            System.out.println(o);
            String reg= "default." + "(.*)" + " BULK UPSERT";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(o);
            String result = null;
            while (matcher.find()) {
                result = matcher.group(1);
            }
            System.out.println(result);
        });
        return null;
    }

    private static String 竖杠切割() {
        String a = "赵壹桥|上海节点";
        String b = "赵壹桥";
        String a1 = a.split("\\|")[0];
        String a2 = a.split("\\|").length>1?a.split("\\|")[1] : "空";
        String[] bA= b.split("\\|");
        String b1 = b.split("\\|")[0];
        String b2 = b.split("\\|").length>1?a.split("\\|")[1] : "空";

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(JSONObject.toJSONString(bA));
        /*String p = "账号信息.电话=2585231808|zo_account.telephone=2585231808";
        String r = p.split("\\|")[0];
        String r2 = p.split("\\|")[1];
        System.out.println(r);
        System.out.println(r2);*/
        return null;
    }
}
