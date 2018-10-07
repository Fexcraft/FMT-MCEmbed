package net.fexcraft.mod.fmt.rendering;

import org.lwjgl.opengl.GL11;

import net.fexcraft.mod.fmt.gameobjects.EditorTileEntity;
import net.fexcraft.mod.lib.api.render.fTESR;
import net.fexcraft.mod.lib.tmt.ModelBase;
import net.fexcraft.mod.lib.tmt.ModelRendererTurbo;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

@fTESR
public class EditorRenderer extends TileEntitySpecialRenderer<EditorTileEntity> {
	
	private static final ResourceLocation res = new ResourceLocation("fmt:editor/null.png");
	private static final ModelRendererTurbo turbo = new ModelRendererTurbo(null, 32, 32);
	static { turbo.addCylinder(0, 0, 0, 4, 16, 48, 1, 1, 1); turbo.setRotationPoint(0, 0, 0); }

    @Override
    public void render(EditorTileEntity tile, double posX, double posY, double posZ, float partialticks, int destroystage, float f){
        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5F, posY, posZ + 0.5F);
        ModelBase.bindTexture(res);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //GL11.glRotated(0, 0, 1D, 0);
        GL11.glRotated(180, 0, 1D, 0);
        //
        //turbo.render();
        if(tile.getPolygons().size() > 0){ tile.getPolygons().forEach((key, value) -> { value.render(tile); }); }
        //
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
