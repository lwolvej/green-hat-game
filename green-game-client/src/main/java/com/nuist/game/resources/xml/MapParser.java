package com.nuist.game.resources.xml;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.binder.DigesterLoader;


/**
 * 解析xml地图
 *
 * @author LwolveJ
 */
public class MapParser {

    public static XmlMap getMapFromXml(String mapName) {
        try {
            DigesterLoader loader = DigesterLoader.newLoader(new FromAnnotationsRuleModule() {
                @Override
                protected void configureRules() {
                    bindRulesFrom(XmlMap.class);
                }
            });
            Digester digester = loader.newDigester();
            return digester.parse(MapParser.class.getResourceAsStream("/map/" + mapName + ".xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
