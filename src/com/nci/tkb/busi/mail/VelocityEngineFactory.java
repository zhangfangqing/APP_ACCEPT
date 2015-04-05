package com.nci.tkb.busi.mail;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityEngineFactory {

	
	public static VelocityEngine createVelocityEngine() {
		VelocityEngine ve = new VelocityEngine();

		//设置velocity资源加载方式为class  
		ve.setProperty("resource.loader", "class");  
        //设置velocity资源加载方式为file时的处理类  
		ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
        
		ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		
		ve.setProperty(VelocityEngine.VM_LIBRARY, "");
		ve.setProperty(VelocityEngine.RUNTIME_LOG, "");
		
		ve.init();
		return ve;
	}
}
