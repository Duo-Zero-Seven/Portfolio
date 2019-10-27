
package facman;

/**
 *
 * @author Dave
 */
public abstract class Entity 
{    
    private String name;
    
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    public Entity(String name) 
    {
        this.setName(name);
    }
}
