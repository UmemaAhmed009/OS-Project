public class Frames {
    public byte[] FrameObj;
    //public byte value;
    //public int[] FrameObj = new int[128]; // byte form ka array

    public Frames()
    {
        FrameObj = new byte[128];
    }
    public void add(byte value, int index)
    {
        FrameObj[index] = value;
    }
    public byte get(int index)
    {
        return FrameObj[index];
    }
}
