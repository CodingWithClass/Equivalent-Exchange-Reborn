package net.multiplemonomials.eer.handler;

import net.multiplemonomials.eer.item.ItemRingFlight;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class PlayerEventHandler
{
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{		
		EntityPlayer player = event.player;
		
		//process Ring of Flight handler
		{
		
			if(player.capabilities.isCreativeMode)
			{
				return;
			}
			
			ItemStack ringFlight = null;
			
			//TODO: does this need to be run on both the client on the server? Maybe it can be only run on one.
			
			//process flight ring
			//this has to be done in here so that flight can be turned off once the ring is no longer in the hotbar
			//however, this has the unfortunate side effect of nuking any other mods which provide flight this way
			//TODO: use IExtendedEntityProperties to fix this
			//http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571567-1-7-2-1-6-4-eventhandler-and
			for(int index = 0; index < InventoryPlayer.getHotbarSize(); ++index)
			{
				if(player.inventory.mainInventory[index] != null && player.inventory.mainInventory[index].getItem() instanceof ItemRingFlight)
				{
					if(player.inventory.mainInventory[index].getItemDamage() > 0)
					{
						ringFlight = player.inventory.mainInventory[index];
					}
				}
			}
			
			
			if(ringFlight != null)
			{
				player.capabilities.allowFlying = true;
			}
			else
			{
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
			}
		}
	}
}
