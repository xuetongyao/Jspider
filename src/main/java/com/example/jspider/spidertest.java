package com.example.jspider;

import net.sf.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class spidertest {
    public static HashMap<String, String> convertCookie(String cookie) {
        HashMap<String, String> cookiesMap = new HashMap<String, String>();
        String[] items = cookie.trim().split(";");
        for (String item:items) cookiesMap.put(item.split("=")[0], item.split("=")[1]);
        return cookiesMap;
    }
    public static void main(String[] args) throws IOException {
        String  url = "https://jam4.sapjam.com/home";
        String jamCookie = "_pk_id.2bcdec4d-0cbd-440f-9602-6bdee004700f.89e4=49bde12a3f83bd41.1648623574.0.1652350728..; _swa_v_ref.2bcdec4d-0cbd-440f-9602-6bdee004700f.89e4=%5B%22%22%2C%22%22%2C1652350729%2C%22https%3A%2F%2Faccounts.sap.com%2F%22%5D; _swa_v_ses.2bcdec4d-0cbd-440f-9602-6bdee004700f.89e4=*; _swa_v_id.2bcdec4d-0cbd-440f-9602-6bdee004700f.89e4=b963803d1366ce51.1648623574.3.1652350745.1650527449.";
        HashMap<String, String>h = new HashMap<>();
        h = convertCookie(jamCookie);
        h.put("_ct_sso","jamatsap.com");
        h.put("_ct_remember","dbE1zG39qNM");
        h.put("JTENANTSESSIONID_r5gzobttiv","1sDi26LuCTLqNOodWOCJYKk4u7uGzfIn8aBi756T7uo%3D");
        h.put("BIGipServereu1internal.factory.customdomain","!4IyG+6tYoAYMpGErNj2u8iYv3vui4DRBbKSP5/C9l952a8tkanOxQysLS/VvFoJPGhtrcytIS0Jzjyk=");
        h.put("_ct_se","u-Zc8uffBQDDJt-6W35Okg3tYtsZDYAFKW4HFA");
        h.put("_ct_session","ukT72h%2F%2BJR%2FUT9n2faScrghnQr2xPfHqy%2FIb8H6Yn1gLR3yECsT%2F2wnkI7hmEa3WUmd%2FHTy7MXO8wHMz8Yn1jFQIyVKon8OUHK%2F996FeIdIiQXV5mWpqlmmch2TklSezI2B%2BhprjzTmBczquF1Ry6S8CDiIlFc4htkwbz8bg13hugsM%2B8F9tEJBKE0JFxxcY4EFlIGLQUTUgYtGqBvXXky5%2Fsypis278NpnPCjSqO8UiiRNvgRL4Mi%2BpsHuRaHtN3VkbHpYhAvg8db%2F%2BNA%2BkWLPREGfzzt7HjJS8gPBh13G1F3UYH8svkyBT6q04JJwYnNFUXfZDaapX9D3GLKI1jrmTyVT8oiUKcE2W%2FzCc7DsGdOPfkEF03Yq3UoF%2BGslL2g936lbdp1J0rLRA2I%2FeB52%2BSiruXBCbPLwZ0i7hM5qKvpQD13U5mA8O2djfojxFSnvggeLc0n9jvU1sbwZudhUuNGR4cGQo89aeJIhnyqKUP0rU%2FOM7EFBQpRxy8FL0YZzeK31HdKrqDCNMWjHDUfXyDeXMm%2BJDi4AOPAGDYEX3hOwfnUHv0t%2BFJimyBXK3mnX28CFY7xtbjFsSd2ZX8AyNiH8B%2B0BdWPF%2F8QtBxJ2skiTkx0oYWDkB6g2CsJP8MudvxhINhXsVHflCS8ASE%2F65AkmO8XKd7gUnoAwcVO%2Brir7XBrhnzSwWIBeOwRyFzss3l%2FGs2%2Fc2ctbpXmIzoDAprlDJBsnRWqxTSCppdSvNqeNWgvEwvcAYbaYW88HBa42iVPyS4efi6nUI12Ar7lxp%2BRSrp%2BD5XZ%2FpfXbwitiH01Y%3D--iY2%2FexRu7xoueiPi--YQc66gyQ4P8qPKBpCuPUaQ%3D%3D");
        h.put("slo_regular_domains_eu1_r5gzobttiv_r5gzobttiv","H4sIAAAAAAAAAAEwAM%2F%2FTeRrFZPM1i2Ic2Z%2FI7hBd3Jxuv9dHvg2t0yMlUmKTTdWyOwH4r4AfNxJSRRHausgSwmaTTAAAAA%3D");
        h.put("JSESSIONID","3B625DC553BB28B16D5F8006C78717EE");
        System.out.println(h);
        Document doc = Jsoup.connect(url).cookies(convertCookie(jamCookie)).get();
        System.out.println(doc);
    }
}
