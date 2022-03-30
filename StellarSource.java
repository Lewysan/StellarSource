package your_path;

import your_core_path;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class StellarSource {

    protected static StellarCore core;
    public static PluginManager pm = Bukkit.getServer().getPluginManager();

    public static ArrayList<UUID> ss = new ArrayList<>();
    public static ArrayList<Player> mod = new ArrayList<>();
    public static ArrayList<Player> builders = new ArrayList<>();
    public static ArrayList<Player> buildmode = new ArrayList<>();
    public static ArrayList<Player> vanish = new ArrayList<>();
    public static ArrayList<UUID> pinjoin = new ArrayList<>();
    public final ArrayList<Player> cooldown = new ArrayList<>(), nofall = new ArrayList<>();
    public static String clear = " ";

    public static Server server = Bukkit.getServer();
    public static BukkitScheduler sh = server.getScheduler();
    public static final String author = "Tyranzx";

    public static ChatColor red = ChatColor.RED;
    public static ChatColor green = ChatColor.GREEN;
    public static ChatColor yellow = ChatColor.YELLOW;

    public static String coreprefix = c("&4&lSTELLAR-CORE&8: &e");

    public static int getConnectedPlayersSize = server.getOnlinePlayers().size();

    public static boolean allowDrop =  false;
    public static boolean allowPlacing = false;
    public static boolean allowBuilding = false;

    public static String c(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public static void globalMessage(String msg){
        server.broadcastMessage(c(msg));
    }
    public static void stoptasking(int ID){
        sh.cancelTask(ID);
    }
    public static int getGetConnectedPlayersSize(){
        return getConnectedPlayersSize;
    }
    public static Player getConnectedPlayers(){
        for (Player online : server.getOnlinePlayers()){
            return online;
        }
        return null;
    }
    public static void saveConfiguration(FileConfiguration configf, File cfile){
        try {
            configf.save(cfile);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void heal(Player p){
        p.setHealth(20);
        p.setFoodLevel(20);
    }
    public void Fullclear(Player p){
        p.getInventory().clear();
        p.getActivePotionEffects().clear();
    }
    public static int getJugadoresConectadosActuales(){
        return Bukkit.getServer().getOnlinePlayers().size();
    }
    public static int getJugadoresConectadosMaximos(){
        return Bukkit.getServer().getMaxPlayers();
    }
    public Vector vectorxx(Location vector, Location vector2) { // Fisica simple <- (Vectores) (Fuerza) (Distancia)
        double g = -0.08;
        double t = vector2.distance(vector);
        double vX = (1.0 + 0.07 * t) * (vector2.getX() - vector.getX()) / t;
        double vY = (1.0 + 0.03 * t) * (vector2.getY() - vector.getY()) / t - 0.5 * g * t;
        double vZ = (1.0 + 0.07 * t) * (vector2.getZ() - vector.getZ()) / t;
        return new Vector(vX, vY, vZ);
    }
    public static ItemStack getHead(ItemStack item, String id, String texture){
        net.minecraft.server.v1_8_R1.ItemStack head = org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack.asNMSCopy(item);
        net.minecraft.server.v1_8_R1.NBTTagCompound tag = head.hasTag() ? head.getTag() : new net.minecraft.server.v1_8_R1.NBTTagCompound();
        net.minecraft.server.v1_8_R1.NBTTagCompound skullOwnerCompound =  new net.minecraft.server.v1_8_R1.NBTTagCompound();
        net.minecraft.server.v1_8_R1.NBTTagCompound propiedades = new  net.minecraft.server.v1_8_R1.NBTTagCompound();
        net.minecraft.server.v1_8_R1.NBTTagList textures = new  net.minecraft.server.v1_8_R1.NBTTagList();
        net.minecraft.server.v1_8_R1.NBTTagCompound texturesObjeto = new  net.minecraft.server.v1_8_R1.NBTTagCompound();
        texturesObjeto.setString("Value", texture);
        textures.add(texturesObjeto);
        propiedades.set("textures", textures);
        skullOwnerCompound.set("Properties", propiedades);
        skullOwnerCompound.setString("Id", id);
        tag.set("SkullOwner", skullOwnerCompound);
        head.setTag(tag);
        return org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack.asBukkitCopy(head);
    }
}