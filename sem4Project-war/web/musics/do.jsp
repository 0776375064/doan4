<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
/* -----------------------------------------------------------------------------
 Version 2.0
--------------------------------------------------------------------------------
 @author ONEPAY JSC
------------------------------------------------------------------------------*/
--%>
<%@ page import="java.util.*,
                 java.util.ArrayList,
                 java.util.Collections,
                 java.util.Iterator,
                 java.util.Enumeration,
                 java.util.Map,
                 java.net.URLEncoder,
                 javax.crypto.*,
                 javax.crypto.spec.*,
                 java.util.HashMap,
                 java.io.ByteArrayOutputStream" %>

<%!
    // static final String SECURE_SECRET = "your-secure-hash-secret";
    static final String SECURE_SECRET = "6D0870CDE5F24F34F3915FB0045120DB";
    static final char[] HEX_TABLE = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static byte[] decodeHexArray = new byte[103];

    static {
        int i = 0;
        for (byte b : new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}) {
            decodeHexArray[b] = (byte) i++;
        }
        decodeHexArray['a'] = decodeHexArray['A'];
        decodeHexArray['b'] = decodeHexArray['B'];
        decodeHexArray['c'] = decodeHexArray['C'];
        decodeHexArray['d'] = decodeHexArray['D'];
        decodeHexArray['e'] = decodeHexArray['E'];
        decodeHexArray['f'] = decodeHexArray['F'];
    }

    String hashAllFields(Map fields) {
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);
        StringBuffer buf = new StringBuffer();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0) && fieldName.indexOf("vpc_") == 0) {
                buf.append(fieldName + "=" + fieldValue);
                if (itr.hasNext()) {
                    buf.append('&');
                }
            }
        }
        byte[] mac = null;
        try {
            byte[] b = decodeHexa(SECURE_SECRET.getBytes());
            SecretKey key = new SecretKeySpec(b, "HMACSHA256");
            Mac m = Mac.getInstance("HMACSHA256");
            m.init(key);
            m.update(buf.toString().getBytes("UTF-8"));
            mac = m.doFinal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String hashValue = hex(mac);
        return hashValue;

    }

    public static byte[] decodeHexa(byte[] data) throws Exception {
        if (data == null) {
            return null;
        }
        if (data.length % 2 != 0) {
            throw new Exception("Invalid data length:" + data.length);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte b1, b2;
        int i = 0;
        while (i < data.length) {
            b1 = decodeHexArray[data[i++]];
            b2 = decodeHexArray[data[i++]];
            out.write((b1 << 4) | b2);
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }

    static String hex(byte[] input) {
        StringBuffer sb = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }

    void appendQueryFields(StringBuffer buf, Map fields) {

        List fieldNames = new ArrayList(fields.keySet());
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // append the URL parameters
                buf.append(URLEncoder.encode(fieldName));
                buf.append('=');
                buf.append(URLEncoder.encode(fieldValue));
            }
            if (itr.hasNext()) {
                buf.append('&');
            }

        }

    }
%><%
    Map fields = new HashMap();
    for (Enumeration enum1 = request.getParameterNames(); enum1.hasMoreElements(); ) {
        String fieldName = (String) enum1.nextElement();
        String fieldValue = request.getParameter(fieldName);
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }

    String vpcURL = (String) fields.remove("virtualPaymentClientURL");
    fields.remove("SubButL");
    String againLink = request.getHeader("Referer");
    fields.put("AgainLink", againLink);
    if (SECURE_SECRET != null && SECURE_SECRET.length() > 0) {
        String secureHash = hashAllFields(fields);
        fields.put("vpc_SecureHash", secureHash);
    }

    StringBuffer buf = new StringBuffer();
    buf.append(vpcURL).append('?');

    appendQueryFields(buf, fields);
    response.sendRedirect(buf.toString());
%>
