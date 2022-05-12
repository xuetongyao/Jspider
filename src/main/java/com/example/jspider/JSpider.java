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


/**
 * Example program to list links from a URL.
 */
public class JSpider {
   // String queryString;
    public int getWikiPageNum(String url)throws  IOException {
        Document doc = Jsoup.connect(url).get();
        Elements searchResultNum = doc.select("p.search-results-count");
        String[] pageNum  = searchResultNum.text().split("\\s+");
        String pageNumString = new StringBuilder(pageNum[3]).deleteCharAt(pageNum[3].length()-1).toString();
        int pageNumRes = Integer.parseInt(pageNumString);
        return pageNumRes;
    }
    public void JSpiderWikiOnePage(String url) throws IOException{

        print("Fetching %s...", url);
        String ConfluencePagecookie = "_ga=GA1.2.2117684587.1652250498; _gid=GA1.2.1721541720.1652250498; mo.confluence-sso.logoutcookie=a0ba7442-1e6c-44d2-891c-6ba8b5caf566";

        Document doc = Jsoup.connect(url).cookies(convertCookie(ConfluencePagecookie)).get();
        System.out.println(doc);
        Elements searchResultNum = doc.select("p.search-results-count");
        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//        Elements masthead = doc.select("a.last-modified");
        //titles and urls of a page
        Elements searchResult = doc.select("a.search-result-link.visitable");
        //datas of a page
        Elements date = doc.select("span.date");
        //abstracts of a page
        Elements abstracts = doc.select("div.highlights");
//        System.out.println(searchResult.toString());
//        System.out.println(abstracts.toString());
//        print(date.toString());
//        print(doc.title());
//        String[] pageNum  = searchResultNum.text().split("\\s+");
//        String pageNumString = new StringBuilder(pageNum[3]).deleteCharAt(pageNum[3].length()-1).toString();
//        int pageNumRes = Integer.parseInt(pageNumString);
//        int pageNow = Integer.parseInt(pageNum[1]);
//        System.out.println(pageNow);
//        System.out.println(pageNumRes);
        for(int i = 0;i<abstracts.size();i++) {
            Result result = new Result();
            result.setId(Result.resultNum+1);
            result.setTitle(searchResult.get(i).text());
            result.setUrl(searchResult.get(i).attr("abs:href"));
            result.setAbstracts(abstracts.get(i).text());
            result.setDate(date.get(i).text());
            Result.appendToResultList(result);
    //        System.out.println(result.toString());
        }
     //   System.out.println(Result.resultList);
//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.tagName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
//        }

//        print("\nLinks: (%d)", links.size());
//        for (Element link : links) {
//            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
//        }

    }
    public  HashMap<String, String> convertCookie(String cookie) {
        HashMap<String, String> cookiesMap = new HashMap<String, String>();
        String[] items = cookie.trim().split(";");
        for (String item:items) cookiesMap.put(item.split("=")[0], item.split("=")[1]);
        return cookiesMap;
    }
    public JSONArray WikiSpider(String queryString) throws IOException {
        //String url = "https://confluence.successfactors.com/dosearchsite.action?"+"queryString="+queryString;
        //String url = "https://wiki.scn.sap.com/wiki/dosearchsite.action?"+"queryString="+queryString;
        int startIndex = 0;
    //    int pageNum = getWikiPageNum(url);
        String url ="https://confluence.successfactors.com/dosearchsite.action?cql=siteSearch+~+%22jam%22&queryString=jam";
        for(int i = 0;i<1;i++){
            startIndex  = i*10;
            String queryUrl = url+"&startIndex="+startIndex;
            JSpiderWikiOnePage(queryUrl);
        }
        String ConfluencePagecookie = "_ga=GA1.2.2117684587.1652250498; _gid=GA1.2.1721541720.1652250498; mo.confluence-sso.logoutcookie=a0ba7442-1e6c-44d2-891c-6ba8b5caf566";

        JSONArray resultJson = JSONArray.fromObject(Result.resultList);
        String fileName = "wiki_"+queryString+".json";
        File file = new File("./src/main/resources/static/"+fileName);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(resultJson.toString());
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultJson;


    }
    public static void main(String[] args) throws IOException {
//        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String queryString = "sql";


    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}