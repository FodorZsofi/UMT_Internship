import java.util.Arrays;
import java.util.Vector;

public class Partition {

    /**
     *
     * @param vector
     *           In this parameter we will store the first subset, as we build it dinamically
     *           at the end we will have the first subset stored in here
     * @param sum
     *          This parameter stores the sum the first subset should have:
     *          Since we know that s1/length1 = s2/lenght2 => s1*length2 = s2*length1
     *                          => s1*(total_lenght-length1) = (s-s1)*length1
     *                          => s1*total_length - s1*length1 = s*length1 - s1*length1
     *                          => s1 = (s*length1)/total_length (this will always be an integer, since we have an array of integers, 
     *                                                            so the sum of integer will always be an integer, thus 
     *                                                            s*length1 must be divisible by total_length)
     *
     * @param length1
     *          This parameter stores the length of the first subset (i.e length1)
     * @param array
     *          Initial array we want to split
     * @param startIndex
     *          Parsing through the array we add elements from the initial array to the vector, this parameter will tell us
     *          which element to insert
     * @return
     */
    public static boolean partition(Vector<Integer> vector, int sum, int length1, int[] array, int startIndex)
    {
        /*
        *
        * if the length of the subset is 0 and we reached sum = 0 it means we found a splitting of the array
        * that fulfills our expectation
        * otherwise it means we could not find a subarray
        *
        *
        */

        if(length1 == 0)
        {
            if(sum == 0)
                return  true;
            return false;
        }

        /*
        *
        * If we ran out of the bounds of our initial array, we return false, meaning that the array cannot be splitted
        */
        if(startIndex >= array.length)
            return false;


        /*
        * If the current element of the array is smaller than our sum, it means it can be added to the first subset
        * so we add it to the subset, and recall our function with the new subset, new sum, length1-1, and startIndex+1,
        * meaning that we advance in our array
        *
        * If it returns false, we remove the element that was inserted last in the vector
        * */
        if(array[startIndex] <= sum)
        {
            vector.add(array[startIndex]);
            if(partition(vector, sum-array[startIndex], length1-1, array, startIndex+1))
                return true;
            vector.remove(vector.lastElement());
        }

        /*
        * In this case, our vector remains unchanged, we just advance in the array, by incrementing the index, and recalling our function
        * */
        if(partition(vector, sum, length1, array, startIndex+1))
        {
            return true;
        }

        return false;
    }

    /**
     *
     * @param vector
     *          The array we want to split
     * @return
     *         returns true if the vector can be split in two non-empty sets having same average
     *                  false otherwise
     */

    public static boolean init(int[] vector) {

        /*
        * We initialize our total_length (i.e n), our total_sum (i.e sum) and sort the vector,
        * so that our job will be easier
        *
        * Then we iterate through the array and check if the first subset can have length i, so s1 will be an integer.
        * If this condition will be fullfilled we create a new vector and call our partition function, to see if it is
        * possible to split the array in two subsets, one having length i the other one having length n-length1
        * */
        int n = vector.length;
        int sum =0;
        Arrays.sort(vector);

        for(int i = 0; i < n; i++)
        {
            sum+= vector[i];
        }
        boolean out = false;

        /*
         *
         * We iterate through the array and check if the first subset can have length i, so s1 will be an integer.
         * If this condition will be fullfilled we create a new vector and call our partition function, to see if it is
         * possible to split the array in two subsets, one having length i the other one having length n-length1
         * */
        for(int i = 1; i < n; i++)
        {
            if((i*sum)%n == 0)
            {
                Vector<Integer> subset1 = new Vector<>();
                out = partition(subset1, (i*sum)/n, i, vector, 0);
                if(out)
                {
                    break;
                }
            }
        }
        return out;
    }

    public static void main(String[] args)
    {
        int[] vector = {4, 3, 5, 9, 11};
        System.out.println(init(vector));

    }


}
