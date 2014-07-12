package com.pahimar.ee3.item.tool;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.client.util.PowerItemUtils;
import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.interfaces.IChargeable;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.item.ItemEE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemPickaxeDarkMatter extends ItemPickaxe implements IChargeable, IKeyBound
{
	public ItemPickaxeDarkMatter()
	{
		super(PowerItemUtils.MATERIALDARKMATTER);
		
		setUnlocalizedName(Names.Tools.PICKAXE_DARK_MATTER);
		setCreativeTab(CreativeTab.EE3_TAB);
		
		setNoRepair();
		
		maxStackSize = 1;
	}
	
	//not repairable... because it never breaks
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }
	
	//do not decrease durability on block destroyed
	@Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {

        return true;
    }
	
	//do not damage tool
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        return true;
    }
	
	//dig speed increases with durability
	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
    {
        if(block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock)
        {
      		return super.getDigSpeed(itemstack, block, metadata);
        }
        
        //for every charge level, efficiency increases by 3
        return efficiencyOnProperMaterial + PowerItemUtils.computeEfficiencyBonus(itemstack.getItemDamage());
  
    }
	
    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
    	if(key == Key.CHARGE)
    	{
    		PowerItemUtils.bumpChargeOnItem(itemStack);
    	}
    }
    
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, ItemEE.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, ItemEE.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(ItemEE.getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
    }


}