package com.upbos.upm.entity;

/**
 * <b>Application name：</b> DictMapping.java <br>
 * <b>Application describing:</b> <br>
 * <b>Copyright：</b> Copyright &copy; 2017 明医众禾科技（北京）有限责任公司 版权所有。<br>
 * <b>Company：</b> 明医众禾科技（北京）有限责任公司 <br>
 * <b>Date：</b> 2017年10月18日 17:00 <br>
 * <b>author：</b> <a href="mailto:wangjz@miyzh.com"> wangjz </a>
 * <b>version：</b>V2.0.0
 */
public class DictMapping {
    //字典标识
    private String dictKey;
    private String sourceProp;
    private String targetProp;

    public DictMapping(String dictKey, String sourceProp, String targetProp) {
        this.dictKey = dictKey;
        this.sourceProp = sourceProp;
        this.targetProp = targetProp;
    }

    public DictMapping(String sourceProp, String targetProp) {
        this.dictKey = sourceProp;
        this.sourceProp = sourceProp;
        this.targetProp = targetProp;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getSourceProp() {
        return sourceProp;
    }

    public void setSourceProp(String sourceProp) {
        this.sourceProp = sourceProp;
    }

    public String getTargetProp() {
        return targetProp;
    }

    public void setTargetProp(String targetProp) {
        this.targetProp = targetProp;
    }
}
