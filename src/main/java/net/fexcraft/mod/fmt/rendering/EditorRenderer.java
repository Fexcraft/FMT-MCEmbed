package net.fexcraft.mod.fmt.rendering;

import org.lwjgl.opengl.GL11;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.fexcraft.mod.lib.api.render.fTESR;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

@fTESR
public class EditorRenderer extends TileEntitySpecialRenderer<EditorTileEntity> {

    @Override
    public void render(EditorTileEntity tile, double posX, double posY, double posZ, float partialticks, int destroystage, float f){
        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5F, posY, posZ + 0.5F);
        //ModelBase.bindTexture(ModelConstructorCenter.getTexture());
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //GL11.glRotated(0, 0, 1D, 0);
        //GL11.glRotated(180, 0, 1D, 0);
        //
        if(tile.getPolygons().size() > 0){ tile.getPolygons().forEach((key, value) -> { value.render(tile); }); }
        //
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
