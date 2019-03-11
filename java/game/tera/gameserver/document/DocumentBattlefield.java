package tera.gameserver.document;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import rlib.data.AbstractDocument;
import rlib.util.VarTable;
import tera.gameserver.model.battlefields.BattlefieldList;

import java.io.File;

public class DocumentBattlefield extends AbstractDocument<Void> {

    public DocumentBattlefield(File file) {
        super(file);
    }

    @Override
    protected Void create() {
        return null;
    }

    @Override
    protected void parse(Document document) {
        VarTable vars = VarTable.newInstance();

        for(Node list = doc.getFirstChild(); list != null; list = list.getNextSibling())
            if("list".equals(list.getNodeName()))
                for(Node child = list.getFirstChild(); child != null; child = child.getNextSibling())
                    if(child.getNodeType() == Node.ELEMENT_NODE && "battlefield".equals(child.getNodeName())) {
                        vars.parse(child);
                        BattlefieldList battlefield = new BattlefieldList();
                        battlefield.setBattleFieldId(vars.getInteger("id"));
                        battlefield.setMinLevel(vars.getInteger("minLevel"));
                        battlefield.setMaxLevel(vars.getInteger("maxLevel"));
                        battlefield.setName(vars.getString("name"));
                    }
    }
}
