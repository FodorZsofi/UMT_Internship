import org.junit.Assert;
import org.junit.Test;

public class TestPartition {

    @Test
    public void testPartitionAlgorithm()
    {
        int[] vector = {4, 3, 5, 9, 11};
        Partition part = new Partition();


        Assert.assertEquals(false,part.init(vector) );
    }

    @Test
    public void testPartitionAlgorithm2()
    {
        int[] vector = {1,5,4,3,2,6};
        Partition part = new Partition();


        Assert.assertEquals(true,part.init(vector) );
    }

}
