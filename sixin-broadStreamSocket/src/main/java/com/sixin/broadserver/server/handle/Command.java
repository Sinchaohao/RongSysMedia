package com.sixin.broadserver.server.handle;

public interface Command {
	
    public byte[] execute();
    
    public boolean save(Object obj);
    
    public Object get(Object obj);
    
}
