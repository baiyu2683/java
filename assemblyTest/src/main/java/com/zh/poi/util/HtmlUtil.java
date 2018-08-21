package com.zh.poi.util;

/**
 * 
 * html
 * @author zhheng
 */
public class HtmlUtil {

    /**
     * @param htmlContent
     * @return
     */
    public static String perfectHtml(String htmlContent) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
        sb.append("<head>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\" />");
        sb.append("<style type=\"text/css\">");
        sb.append("::-webkit-scrollbar{width:14px;} BODY {padding:0;overflow:hidden;margin:0;border:0 none;font-family:'Microsoft Yahei','΢���ź�',SimSun,'����',Tahoma,Arial;font-size: 9pt;} p { -webkit-margin-after: 0;-webkit-margin-before: 0;}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(specialConvert(htmlContent,false));
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    
    /**
     * @param htmlContent
     * @return
     */
    public static String perfectHtmlForWord(String htmlContent) {
        StringBuilder sb = new StringBuilder("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                "xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\n" +
                "xmlns=\"http://www.w3.org/TR/REC-html40\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val=\"Cambria Math\"/><m:brkBin m:val=\"before\"/><m:brkBinSub m:val=\"--\"/><m:smallFrac m:val=\"off\"/><m:dispDef/><m:lMargin m:val=\"0\"/> <m:rMargin m:val=\"0\"/><m:defJc m:val=\"centerGroup\"/><m:wrapIndent m:val=\"1440\"/><m:intLim m:val=\"subSup\"/><m:naryLim m:val=\"undOvr\"/></m:mathPr></w:WordDocument></xml><![endif]--> ");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(specialConvert(htmlContent,true));
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
    
    private static String specialConvert(String htmlContent, boolean replaceSpace) {
        htmlContent = htmlContent.replace("<br>", "<br/>") // 闭合br
                                 .replaceAll("(<img[^>]*?)>", "$1/>"); // 闭合img
        if (replaceSpace) {
        	htmlContent = htmlContent.replace("&nbsp;", " "); // 空格转换
        }
        return htmlContent;
    }
}
