package com.geektcp.common.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientObf {

    private static final Logger ThyPaTrsKer = LoggerFactory.getLogger(ClientObf.class);

    public ClientObf() {
    }

    public static String ALLATORIxDEMO(String s) {
        int var10000 = 4 << 4 ^ 2 << 2 ^ 3;
        int var10001 = 2 << 3 ^ 5;
        int var10002 = 5 << 3 ^ 4;
        int var10003 = (s = (String) s).length();
        char[] var10004 = new char[var10003];
        boolean var10006 = true;
        int var5 = var10003 - 1;
        var10003 = var10002;
        int var3;
        var10002 = var3 = var5;
        char[] var1 = var10004;
        int var4 = var10003;
        var10001 = var10000;
        var10000 = var10002;

        for (int var2 = var10001; var10000 >= 0; var10000 = var3) {
            var10001 = var3;
            char var6 = s.charAt(var3);
            --var3;
            var1[var10001] = (char) (var6 ^ var2);
            if (var3 < 0) {
                break;
            }

            var10002 = var3--;
            var1[var10002] = (char) (s.charAt(var10002) ^ var4);
        }

        return new String(var1);
    }


    public String e() {
        String a = System.getProperty(ALLATORIxDEMO("$_eB*A.")).toLowerCase();
        ThyPaTrsKer.info(ALLATORIxDEMO("C8b*A.\u0016kW6"), a);
        if (StringUtils.isBlank(a)) {
            ThyPaTrsKer.error(ALLATORIxDEMO("C8b*A.\f\"_kI&\\?Uj"));
            return null;
        } else {
            InputStreamReader in;
            BufferedReader br;
            StringBuffer sb = new StringBuffer();
            String l;
            BufferedReader var10000;
            if (a.startsWith(ALLATORIxDEMO("'E%Y3"))) {
                String a1 = ALLATORIxDEMO("dN\"BdO*Xk\u00038U8\u0003/I=E(I8\u0003=E9X>M'\u0003/A\"\u0003\"Hd\\9C/Y(X\u0014Y>E/");
                String a2 = ALLATORIxDEMO("dN\"Bd_>H$\fdN\"BdO*Xk\u00038U8\u0003/I=E(I8\u0003=E9X>M'\u0003/A\"\u0003\"Hd\\9C/Y(X\u0014Y>E/");
                String[] var32 = new String[3];
                var32[0] = ALLATORIxDEMO("dN\"Bd_#");
                var32[1] = ALLATORIxDEMO("\u0001(");
                var32[2] = a1;
                try {
                    InputStream in1 = Runtime.getRuntime().exec(var32).getInputStream();
                    in = new InputStreamReader(in1);
                    var10000 = new BufferedReader(in);
                    while ((l = var10000.readLine()) != null) {
                        sb.append(l);
                    }
                    String var4 = sb.toString().trim();
                    if (StringUtils.isBlank(var4)) {
                        ThyPaTrsKer.error(ALLATORIxDEMO("?D.\f\"HkE8\f.A;X2\fj"));
                    }

                    if (var4.length() != 36) {
                        ThyPaTrsKer.error((new StringBuilder()).insert(0, ALLATORIxDEMO("X#IkE/\f\"_kB$XkO$^9I(Xk^._>@?\u0000k\\'I*_.\f(D.O \f?D\"_k@.B,X#\u0016k")).append(a).toString());
                    }

                    if (StringUtils.isBlank(var4) || var4.length() != 36) {
                        ThyPaTrsKer.error(ALLATORIxDEMO("K.XkE/\f-M\"@.Hg\f(D.O \f?D\"_kE8_>IkM)C>Xk^$C?o&Hj"));
                        var32[2] = a2;
                        InputStream in2 = Runtime.getRuntime().exec(var32).getInputStream();
                        in = new InputStreamReader(in2);
                        var10000 = new BufferedReader(in);
                        while ((l = var10000.readLine()) != null) {
                            sb.append(l);
                        }
                        var4 = sb.toString().trim();
                        if (StringUtils.isBlank(var4)) {
                            ThyPaTrsKer.error(ALLATORIxDEMO("?D.\f\"HkE8\f.A;X2\fj"));
                        }
                        if (var4.length() != 36) {
                            ThyPaTrsKer.error((new StringBuilder()).insert(0, ALLATORIxDEMO("X#IkE/\f\"_kB$XkO$^9I(Xk^._>@?\u0000k\\'I*_.\f(D.O \f?D\"_k@.B,X#\u0016k")).append(a).toString());
                        }
                        return var4;
                    }else{
                        return var4;
                    }
                } catch (IOException var25) {
                    ThyPaTrsKer.error(var25.getMessage(), var25);
                    return null;
                }
            } else {
                String c = ALLATORIxDEMO("<A\"OkO8\\9C/Y(XkK.Xky\u001ee\u000f");

                try {
                    InputStream var1 = Runtime.getRuntime().exec(c).getInputStream();
                    in = new InputStreamReader(var1);
                    br = new BufferedReader(in);
                    sb = new StringBuffer();
                    var10000 = br;
                    while (var10000.readLine() != null && var10000.readLine() != ALLATORIxDEMO("k") && (a = var10000.readLine()) != ALLATORIxDEMO("y\u001ee\u000f")) {
                        sb.append(a);
                    }
                    return sb.toString().trim();
                } catch (Exception var27) {
                    ThyPaTrsKer.error(var27.getMessage(), var27);
                    return null;
                }
            }
        }
    }

}
