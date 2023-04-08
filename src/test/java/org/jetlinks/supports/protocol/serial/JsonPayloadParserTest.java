package org.jetlinks.supports.protocol.serial;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hswebframework.web.bean.Copier;
import org.hswebframework.web.bean.FastBeanCopier;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JsonPayloadParserTest {

    private JsonPayloadParser parser = new JsonPayloadParser(new ObjectMapper());

    @Test
    public void parse() throws IOException, IllegalAccessException, InstantiationException {
        String jsonStr = "{\"field1\":\"value1\",\"field2\":2,\"field3\":3,\"field4\":\"2023-04-08 00:00:00\",\"field5\":5.5,\"field6\":6.6}";
        JSONObject jsonObj = parser.parse("", jsonStr.getBytes(StandardCharsets.UTF_8));
        System.out.println("jsonObj =" + jsonObj);
        Assert.assertNotNull(jsonObj);

        Class<_Message> targetClazz = _Message.class;

        _Message rst = targetClazz.newInstance();
        FastBeanCopier.copy(jsonObj, rst);
//        _Copier copier = new _Copier();
//        copier.copy(jsonObj, rst);
        System.out.println("rst = " + rst);
    }

    private static class _Copier implements Copier {

        public void copy(Object s, Object t, java.util.Set ignore, org.hswebframework.web.bean.Converter converter){
            try{
                java.util.Map $$__source=(java.util.Map)s;
                JsonPayloadParserTest._Message $$__target=(JsonPayloadParserTest._Message)t;
                if(!ignore.contains("field1")){
                    if($$__source.get("field1")!=null){
                        java.lang.String field1=(java.lang.String)(converter.convert((Object)($$__source.get("field1")),java.lang.String.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field1!=null){
                            $$__target.setField1(field1);
                        }
                    }
                }
                if(!ignore.contains("field2")){
                    if($$__source.get("field2")!=null){
                        java.lang.Integer field2=(java.lang.Integer)(converter.convert((Object)($$__source.get("field2")),java.lang.Integer.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field2!=null){
                            $$__target.setField2(field2);
                        }
                    }
                }
                if(!ignore.contains("field3")){
                    if($$__source.get("field3")!=null){
                        java.lang.Long field3=(java.lang.Long)(converter.convert((Object)($$__source.get("field3")),java.lang.Long.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field3!=null){
                            $$__target.setField3(field3);
                        }
                    }
                }
                if(!ignore.contains("field4")){
                    if($$__source.get("field4")!=null){
                        java.util.Date field4=(java.util.Date)(converter.convert((Object)($$__source.get("field4")),java.util.Date.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field4!=null){
                            $$__target.setField4(field4);
                        }
                    }
                }
                if(!ignore.contains("field5")){
                    if($$__source.get("field5")!=null){
                        java.lang.Float field5=(java.lang.Float)(converter.convert((Object)($$__source.get("field5")),java.lang.Float.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field5!=null){
                            $$__target.setField5(field5);
                        }
                    }
                }
                if(!ignore.contains("field6")){
                    if($$__source.get("field6")!=null){
                        java.lang.Double field6=(java.lang.Double)(converter.convert((Object)($$__source.get("field6")),java.lang.Double.class,org.hswebframework.web.bean.FastBeanCopier.EMPTY_CLASS_ARRAY));
                        if(field6!=null){
                            $$__target.setField6(field6);
                        }
                    }
                }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage(),e);
            }

        }
    }

    @Getter @Setter @ToString
    public static class _Message {

        private String  field1;

        private Integer field2;

        private Long    field3;

        private Date    field4;

        private Float   field5;

        private Double  field6;

    }
}