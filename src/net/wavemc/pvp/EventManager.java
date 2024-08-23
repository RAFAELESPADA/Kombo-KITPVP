package net.wavemc.pvp;

import net.wavemc.pvp.listener.RDMAutomatic;

public class EventManager {
  private RDMAutomatic rdmAutomatic = null;
  
  public boolean isRunningRDM() {
    return (this.rdmAutomatic != null);
  }
  
  public RDMAutomatic getRdmAutomatic() {
    return this.rdmAutomatic;
  }
  
  public void setRdmAutomatic(RDMAutomatic rdmAutomatic) {
    this.rdmAutomatic = rdmAutomatic;
  }
}