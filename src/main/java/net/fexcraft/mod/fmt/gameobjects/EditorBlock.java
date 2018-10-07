package net.fexcraft.mod.fmt.gameobjects;

import java.util.Random;

import net.fexcraft.mod.fmt.FMT;
import net.fexcraft.mod.lib.api.block.fBlock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@fBlock(modid = FMT.MODID, name = "editor", tileentity = EditorTileEntity.class)
public class EditorBlock extends BlockContainer {

    public static final AxisAlignedBB AABB = new AxisAlignedBB(.25D, 0D, .25D, .75D, .25D, .75D);
    public static EditorBlock INSTANCE;

    public EditorBlock(){
        super(Material.IRON, MapColor.BLACK);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setSoundType(SoundType.METAL);
        //
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(50.0F);
        this.setResistance(280.0F);
        //
        INSTANCE = this;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta){
        return new EditorTileEntity();
    }

    @Override
    public boolean isFullBlock(IBlockState state){
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state){
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){ return AABB; }

    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos){ return AABB.offset(pos); }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState();
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        world.setBlockState(pos, state, 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState(); //TODO
    }

    @Override
    public int getMetaFromState(IBlockState state){
        return 0;//TODO
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
    	if(!world.isRemote){
            //TODO break notify to TE
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        if(!world.isRemote && hand == EnumHand.MAIN_HAND){
            /*EditorTileEntity tile = (EditorTileEntity)world.getTileEntity(pos); if(tile == null) return false;
            if(!tile.getPolygons().containsKey("core")){
            	tile.getPolygons().put("core", new TMTCompound());
            }
            TMTCuboid cube = new TMTCuboid();
            cube.apply("pos_y", tile.getPolygons().get("core").getEditorShapes().size() * -8, null);
            tile.getPolygons().get("core").addShape(-1, cube);
            tile.update();*/
        }
        return false;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        if(world.getTileEntity(pos) != null){
            //TODO check for model
        }
        return new ItemStack(INSTANCE);
    }
    
    @Override
    public int quantityDropped(Random random){
        return 0;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Items.AIR;
    }
    
    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state){
        return new ItemStack(this);
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos){
        return false;
    }

}
