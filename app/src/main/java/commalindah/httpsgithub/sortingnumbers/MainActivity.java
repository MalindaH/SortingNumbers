/**
 * Name: Linhui Huang (Malinda)
 * Course: CS30S
 * Teacher: Mr. Hardman
 * Lab #2, Program #0
 * Date Last Modified: 4/3/2018
 */
package commalindah.httpsgithub.sortingnumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText[] numberInputs;
    private TextView mErrorMessage;
    private TextView mSortedLists;

    private double[] toSort;

    private int insertionSteps = 0;
    private int selectionSteps = 0;
    private int quickSteps = 0;

    @Override
    /**
     * onCreate is the method that is called when the activity is created the first time
     *
     * @param savedInstanceState is a Bundle of information that is used to
     *                           restore this activity to a previous state
     * @return Nothing is returned
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int resourceId;

        mErrorMessage = (TextView) findViewById(R.id.tv_error_message);
        mSortedLists = (TextView) findViewById(R.id.tv_sorted_lists);

        numberInputs = new EditText[10];

        for( int i = 0; i < numberInputs.length; i++)
        {
            resourceId = this.getResources().getIdentifier( "et_num_"+ (i+1), "id", this.getPackageName() );

            numberInputs[i] = (EditText) findViewById( resourceId );
        }

        for( int j = 0; j< numberInputs.length; j++ )
        {
            numberInputs[j].addTextChangedListener(numberInputWatcher);
        }
    }

    private TextWatcher numberInputWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        /**
         * afterTextChanged is the code that will be run after the text has been changed
         * in a specific Editable object
         *
         * @param editable is the object that has text changed within it
         * @return Nothing is returned
         */
        public void afterTextChanged(Editable editable)
        {
            String errorMessage = "You must enter numbers in the following inputs:\n";
            String results = "";

            boolean inputNotFilled = false;

            boolean[] textsFilled = new boolean[10];

            for( int j = 0; j < numberInputs.length; j++ )
            {
                if( numberInputs[j].getText().length() > 0 )
                {
                    textsFilled[j] = true;
                }
                else
                {
                    textsFilled[j] = false;
                }
            }

            for( int i = 0; i < textsFilled.length; i++ )
            {
                if( textsFilled[i] == false )
                {
                    errorMessage += (i+1) + ", ";
                    inputNotFilled = true;
                }
            }

            if( inputNotFilled == true )
            {
                mSortedLists.setText("");
                mErrorMessage.setText( errorMessage );
            }
            else
            {
                toSort = new double[10];

                for( int j = 0; j < numberInputs.length; j++ )
                {
                    toSort[j] = Double.parseDouble( numberInputs[j].getText().toString() );
                }

                results += insertionSort();

                results += selectionSort();

                quickSort( toSort, 0, toSort.length - 1);

                quickSteps--;

                results += "Quick Sort Result:\n";

                for( int k = 0; k < toSort.length; k++ )
                {
                    results += toSort[k] + ", \t";
                }

                results += "\nThis took " + quickSteps + " steps to complete.\n\n";

                mSortedLists.setText( results );

            }
        }
    };

    /**
     * insertionSort uses the Insertion Sort algorithm to sort a list of items in ascending order
     *
     * @param "" There are no parameters
     * @return a String that displays the sorted list and how many steps it took
     */
    private String insertionSort()
    {
        //You will not need this for your assignment
        double[] temp = new double[10];

        //You will not need this for your assignment
        String result;

        //key might need to be a different data type...int/string
        double key;
        int index;

        ////You will not need this for loop
        for( int i = 0; i < temp.length; i++ )
        {
            temp[i] = toSort[i];
        }

        //This is where insertion sort starts
        for( int j = 1; j < temp.length; j++)
        {
            key = temp[j];
            index = j - 1;

            while( index >= 0 && temp[index] > key )
            {
                temp[index+1] = temp[index];
                index = index - 1;

                insertionSteps++;
            }

            temp[index+1] = key;

            insertionSteps++;
        }

        result = "Insertion Sort Result:\n";

        for( int k = 0; k < temp.length; k++ )
        {
            result += temp[k] + ", \t";
        }

        result += "\nThis took " + insertionSteps + " steps to complete.\n\n";

        return result;
    }

    /**
     * selectionSort uses the Selection Sort algorithm to sort a list of items in ascending order
     *
     * @param "" There are no parameters
     * @return a String that will display the sorted list and how many steps it took
     */
    private String selectionSort()
    {
        double[] temp = new double[10];

        String result;

        int minIndex;
        double toSwap;

        for( int i = 0; i < temp.length; i++ )
        {
            temp[i] = toSort[i];
        }

        for( int j = 0; j < temp.length - 1; j++ )
        {
            minIndex = j;

            for( int k = j+1; k < temp.length; k++)
            {
                if( temp[k] < temp[minIndex] )
                {
                    minIndex = k;
                }

                selectionSteps++;
            }

            toSwap = temp[minIndex];
            temp[minIndex] = temp[j];
            temp[j] = toSwap;

            selectionSteps++;
        }

        result = "Selection Sort Result:\n";

        for( int m = 0; m < temp.length; m++ )
        {
            result += temp[m] + ", \t";
        }

        result += "\nThis took " + selectionSteps + " steps to complete.\n\n";

        return result;
    }

    /**
     * quickSort uses the Quick Sort algorithm to sort a list in ascending order
     *
     * @param array is the array we are sorting
     * @param low is the beginning index of the section of the array we would like to sort
     * @param high is the ending index of the section of the array we would like to sort
     * @return Nothing is returned
     */
    private void quickSort( double[] array, int low, int high )
    {
        int middle;
        double pivot;

        int i;
        int j;

        double toSwap;

        if( low < high )
        {
            middle = low + (high-low)/2;
            pivot = array[middle];

            i = low;
            j = high;

            while( i <= j )
            {
                while( array[i] < pivot )
                {
                    i++;

                    quickSteps++;
                }

                while( array[j] > pivot )
                {
                    j--;

                    quickSteps++;
                }

                if( i <= j )
                {
                    toSwap = array[i];
                    array[i] = array[j];
                    array[j] = toSwap;
                    i++;
                    j--;
                }

                quickSteps++;
            }

            if( low < j )
            {
                quickSort( array, low, j );

                quickSteps++;
            }

            if( high > i )
            {
                quickSort( array, i, high );

                quickSteps++;
            }
        }

        quickSteps++;
    }
}








