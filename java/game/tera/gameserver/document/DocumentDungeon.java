package tera.gameserver.document;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import rlib.data.AbstractDocument;
import rlib.util.VarTable;
import tera.gameserver.model.dungeons.DungeonList;

import java.io.File;

public class DocumentDungeon extends AbstractDocument<Void> {

    public DocumentDungeon(File file)
    {
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
                    if(child.getNodeType() == Node.ELEMENT_NODE && "dungeon".equals(child.getNodeName())) {
                        vars.parse(child);
                        DungeonList dungeon = new DungeonList();
                        dungeon.setDungeonId(vars.getInteger("id"));
                        dungeon.setDungeonMaxLevel(vars.getInteger("dungeonMaxLevel"));
                        dungeon.setDungeonMinLevel(vars.getInteger("dungeonMinLevel"));
                        dungeon.setMinItemLevel(vars.getInteger("minItemLevel"));
                        dungeon.setName(vars.getString("name"));
                    }

    }
}
