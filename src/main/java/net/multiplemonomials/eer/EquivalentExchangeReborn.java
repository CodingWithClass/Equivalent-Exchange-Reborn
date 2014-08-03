package net.multiplemonomials.eer;

import net.multiplemonomials.eer.configuration.ConfigurationHandler;
import net.multiplemonomials.eer.exchange.EnergyRegistry;
import net.multiplemonomials.eer.exchange.EnergyValuesDefault;
import net.multiplemonomials.eer.handler.CraftingHandler;
import net.multiplemonomials.eer.handler.FuelHandler;
import net.multiplemonomials.eer.handler.GuiHandler;
import net.multiplemonomials.eer.init.ModBlocks;
import net.multiplemonomials.eer.init.ModItems;
import net.multiplemonomials.eer.network.PacketHandler;
import net.multiplemonomials.eer.proxy.IProxy;
import net.multiplemonomials.eer.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import java.io.File;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, certificateFingerprint = Reference.FINGERPRINT, version = Reference.VERSION)
public class EquivalentExchangeReborn
{
    @Instance(Reference.MOD_ID)
    public static EquivalentExchangeReborn instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    
    public static EnergyValuesDefault emcDefaultValues = null;


    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
    {

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID.toLowerCase() + File.separator);

        PacketHandler.init();

        proxy.registerKeybindings();

        ModItems.init();

        ModBlocks.init();
        
        emcDefaultValues = new EnergyValuesDefault();
        emcDefaultValues.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Register the GUI Handler
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

        // Initialize mod tile entities
        proxy.registerTileEntities();

        // Initialize custom rendering and pre-load textures (Client only)
        proxy.initRenderingAndTextures();

        // Register the Items Event Handler
        proxy.registerEventHandlers();

        CraftingHandler.init();

        // Register our fuels
        GameRegistry.registerFuelHandler(new FuelHandler());
        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	EnergyRegistry.getInstance();
    }

    @EventHandler
    public void handleIMCMessages(IMCEvent event)
    {

    }
}
