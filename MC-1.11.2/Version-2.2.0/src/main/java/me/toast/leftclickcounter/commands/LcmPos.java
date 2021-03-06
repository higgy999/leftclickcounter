package me.toast.leftclickcounter.commands;

import me.toast.leftclickcounter.LeftClickCounterMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class LcmPos extends CommandBase 
{

	private LeftClickCounterMod mod;
	
	public LcmPos(LeftClickCounterMod mod)
	{
		this.mod = mod;
	}
	
	@Override
	public String getName() 
	{
		return "LcmPos";
	}

	@Override
	public String getUsage(ICommandSender sender) 
	{
		return "/" + getName() + " <new Xpos>" + "<new Ypos>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(args.length == 0)
		{
			sender.sendMessage(new TextComponentString(getUsage(sender)));
		}
		else 
		{
			try
			{
				int newposX = Integer.parseInt(args[0]);
				int newposY = Integer.parseInt(args[1]);
				//Change the setting
				mod.getSettings().setposX(newposX);
				mod.getSettings().setposY(newposY);
				//Saving the settings
				mod.getSettings().saveConfig();
			}
			catch(Exception ex)
			{
				sender.sendMessage(new TextComponentString(getUsage(sender)));
			}
		}
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
        return 0;
    }
    
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
    }

}