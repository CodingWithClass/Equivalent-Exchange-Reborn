package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalBag;
import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalChest;
import com.pahimar.ee3.client.gui.inventory.GuiAludel;
import com.pahimar.ee3.client.gui.inventory.GuiCalcinator;
import com.pahimar.ee3.client.gui.inventory.GuiCondenser;
import com.pahimar.ee3.client.gui.inventory.GuiGlassBell;
import com.pahimar.ee3.client.gui.inventory.GuiTransmutationTablet;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.inventory.ContainerAludel;
import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.inventory.ContainerCondenser;
import com.pahimar.ee3.inventory.ContainerGlassBell;
import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.inventory.InventoryAlchemicalBag;
import com.pahimar.ee3.reference.GuiIds;
import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;
import com.pahimar.ee3.tileentity.TileEntityAludel;
import com.pahimar.ee3.tileentity.TileEntityCalcinator;
import com.pahimar.ee3.tileentity.TileEntityCondenser;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == GuiIds.ALCHEMICAL_CHEST)
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileEntityAlchemicalChest);
        }
        if (id == GuiIds.CONDENSER)
        {
            TileEntityCondenser tileEntityCondenser = (TileEntityCondenser) world.getTileEntity(x, y, z);
            return new ContainerCondenser(player.inventory, tileEntityCondenser);
        }
        else if (id == GuiIds.GLASS_BELL)
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new ContainerGlassBell(player.inventory, tileEntityGlassBell);
        }
        else if (id == GuiIds.ALCHEMICAL_BAG)
        {
            return new ContainerAlchemicalBag(player, new InventoryAlchemicalBag(player.getHeldItem()));
        }
        else if (id == GuiIds.CALCINATOR)
        {
        	TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
        	return new ContainerCalcinator(player.inventory, tileEntityCalcinator);
        }
        else if (id == GuiIds.ALUDEL)
        {
        	TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
        	return new ContainerAludel(player.inventory, tileEntityAludel);
        }
        else if (id == GuiIds.TRANSMUTATION_TABLET)
        {
        	TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(x, y, z);
        	return new ContainerTransmutationTablet(player.inventory, tileEntityTransmutationTablet);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == GuiIds.ALCHEMICAL_CHEST)
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileEntityAlchemicalChest);
        }
        if (id == GuiIds.CONDENSER)
        {
            TileEntityCondenser tileEntityCondenser = (TileEntityCondenser) world.getTileEntity(x, y, z);
            return new GuiCondenser(player.inventory, tileEntityCondenser);
        }
        else if (id == GuiIds.GLASS_BELL)
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new GuiGlassBell(player.inventory, tileEntityGlassBell);
        }
        else if (id == GuiIds.ALCHEMICAL_BAG)
        {
            return new GuiAlchemicalBag(player, new InventoryAlchemicalBag(player.getHeldItem()));
        }
        else if (id == GuiIds.CALCINATOR)
        {
        	TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
        	return new GuiCalcinator(player.inventory, tileEntityCalcinator);
        }
        else if (id == GuiIds.ALUDEL)
        {
        	TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
        	return new GuiAludel(player.inventory, tileEntityAludel);
        }
        else if (id == GuiIds.TRANSMUTATION_TABLET)
        {
        	TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(x, y, z);
        	return new GuiTransmutationTablet(player.inventory, tileEntityTransmutationTablet);
        }

        return null;
    }
}
