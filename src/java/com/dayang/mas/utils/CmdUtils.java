package com.dayang.mas.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class CmdUtils {
	
	static Options opts = new Options();
	
	static {
		opts.addOption("h", "help", false, "the command help");
		
		opts.addOption("ip", true, "The ip of mas server");
		opts.addOption("port", true, "The port of mas server");
		
		opts.addOption("app", "app-id", true, "The App ID of Mas Server");
		opts.addOption("pwd", "password", true, "The password of APP");
		
		opts.addOption("mobile", "mobile-number", true, "The phone numbers. Separate by \",\"");
		opts.addOption("msg", "message", true, "The sms content.");
		opts.addOption("code", "xcode", true, "The xCode of sms");
		
		@SuppressWarnings("static-access")
		Option priority = OptionBuilder.hasArg()
			.isRequired(false)
	        .withDescription("The sms gateway priority >= 0, Default 1. 0 is highest level.")
	        .create( "priority" );

		opts.addOption(priority);
	}
	
	public static CommandLine parseCmd(String[] args) throws ParseException {
		CommandLineParser parser = new PosixParser();
		CommandLine cl = parser.parse(opts, args);
		
		return cl;
	}
	
	public static void printHelp() {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("A Java tools to sent message to mas gate server.", opts);
	}
}
