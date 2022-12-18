package com.geektcp.common.client;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.geektcp.common.core.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class SomeClass {

    @Value("${license:123}")
    private String l;

    public boolean thy() {
        String var1;
        if (StringUtils.isEmpty(var1 = (new ClientObf()).e())) {
            log.error(thy("dnKcGk\u0002{M/EjV/OnAgKaG/kK\u000e/RcGnQj\u0002lJjAd\u0002{JfQ/K|QzG."));
            return false;
        } else {
            String H = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDloXjSy7j1TAgZJmtSII3mclJZ1EUMzdSmmLyoFklW5XRcl2SC+q5JP7T2WRAFffH6fPL1+SfQXPqI3AqZ1f7AmpmjuzBt0nO3DzExoepjvXxrfBoLPR32dhTtpgE/qLCfwRo2m9vp92gikWjutuoqHFLz2C02ltxN47cB/uszywIDAQAB";
            RSA var2 = new RSA((String)null, H);
            String var7;
            if (StringUtils.isEmpty(var7 = new String(var2.decrypt(Base64.getDecoder().decode(l.getBytes(StandardCharsets.UTF_8)), KeyType.PublicKey), StandardCharsets.UTF_8))) {
                log.error(thy("KGlPvR{K`L/DnKcGk\u000e/RcGnQj\u0002lMaVnA{\u0002\\cIv@r」NfAjL|G2Yr"), l);
                return false;
            } else {
                String[] var3;
                if ((var3 = var7.split(thy(")"))).length != 3) {
                    log.error(thy("KGlPvR{K`L/DnKcGk\u000e/RcGnQj\u0002lMaVnA{\u0002\\cIv@r」NfAjL|G2Yr"), l);
                }

                if (!StringUtils.contains(var3[1], var1)) {
                    log.error(thy("NfAjL|G/f`G|\u0002aM{\u0002mGcMaE/V`\u0002{Jj\u0002lW}PjL{\u0002gM|V#\u0002\u007fNjC|G/A`L{ClV/qNd[m_"));
                    return false;
                } else if (StringUtils.isBlank(var1 = var7.substring(var7.length() - 10))) {
                    log.error(thy("NfAjL|G/rnP|KaE/DnKcGkＮ\u007fNjC|G/A`L{ClV/qNd[m_"));
                    return false;
                } else {
                    SimpleDateFormat var8 = new SimpleDateFormat(thy("[v[v\u000fBo\"Fk"));
                    var3 = null;

                    Date var9;
                    try {
                        var9 = DateUtils.endDateTime(var8.parse(var1));
                    } catch (ParseException var4) {
                        log.error(thy("[Jj\u0002hGaG}C{Gk\u0002cKlGaQj\u0002fLiM}OnVfMa\u0002fQ/L`V/DfNcGk\u0002fL/A`P}GlVc[#\u0002\u007fNjC|G/A`L{ClV/qNd[m_"));
                        return false;
                    }

                    Date var5 = new Date();
                    boolean var6 = var9.getTime() > var5.getTime();
                    if (!var6) {
                        log.error(thy("CKlGaQj\u0002gC|\u0002jZ\u007fK}Gk"));
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    public static String thy(String a) {
        int var10001 = 1 << 3 ^ 2 ^ 5;
        int var10002 = 4 << 3 ^ 2;
        int var10003 = (a = (String)a).length();
        char[] var10004 = new char[var10003];
        boolean var10006 = true;
        int var5 = var10003 - 1;
        var10003 = var10002;
        int var3;
        var10002 = var3 = var5;
        char[] var1 = var10004;
        int var4 = var10003;
        boolean var6 = true;
        int var10000 = var10002;

        for(int var2 = var10001; var10000 >= 0; var10000 = var3) {
            var10001 = var3;
            char var7 = a.charAt(var3);
            --var3;
            var1[var10001] = (char)(var7 ^ var2);
            if (var3 < 0) {
                break;
            }

            var10002 = var3--;
            var1[var10002] = (char)(a.charAt(var10002) ^ var4);
        }

        return new String(var1);
    }

    public SomeClass() {
    }
}
