public class Frames {
    public int value;
    public int[] FrameObj = new int[128];
    //public int[] FrameObj = new int[128]; // byte form ka array

    // public void Frames()
    // {
        
    // }
    public void add(int value, int index)
    {
        FrameObj[index] = value;
    }
    public int get(int index)
    {
        return FrameObj[index];
    }
}
