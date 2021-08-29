function findMin(array){
    var min = 100;
    for(var i = 0; i<array.length; i++){
        if(array[i]<min){
            min = array[i];
        } 
    }
    return min;
}
function findMax(array){
    var max = 0;
    for(var i = 0; i<array.length; i++){
        if(array[i]>max){
            max = array[i];
        } 
    }
    return max;
}
function calcSum(array){
    var sum = 0;
    for(var i = 0; i<array.length; i++){
        sum+=array[i];
    }
    return sum;
}
function calcMean(array){
    return (calcSum(array)/array.length);
}
function calcMedian(array){
    array.sort(function(a,b){
        return a-b;
    });
    if(array.length%2 == 0){
        return ((array[((array.length)/2)-1] + array[((array.length)/2)])/2);
    }
    else{
        return (array[((array.length+1)/2)-1]);
    }
}
function calcMode(array){
    var modeTally = [];
    var mode = [];
    for(var i = 0; i<100; i++){
        modeTally.push(0);
    }
    for(var i = 0; i<array.length; i++){
        modeTally[array[i]]++;
    }
    var max = findMax(modeTally);
    for(var i = 0; i<modeTally.length; i++){
        if(modeTally[i] == max){
            mode.push(i.toFixed(2));
        }
    }
    return mode;
}
function calcStdDev(array){
    var mean = calcMean(array);
    var numeratorBase = 0;
    for(var i = 0; i<array.length; i++){
        numeratorBase += (array[i] - mean)*(array[i] - mean);
    }
    var stdDev = Math.sqrt(numeratorBase/array.length);
    return stdDev;
}
  
function calcVariance(array){
    return Math.pow(calcStdDev(array),2);
}
function performStatistics(){
    //check to make sure each value is between 0 and 100 (1-99)
    // 1 5 7 1 10 99 44 52 15 71 25 5 1
    var arrayImport = document.getElementById("array").value;
    var array = arrayImport.split(" ").map(Number);
    for(var i = 0; i<array.length; i++){
        if(parseInt(array[i])){
            if(0<array[i]){
                if(array[i]<100){}
                else{
                    alert("Index in input is too large.")
                    return false;
                }
            }
            else{
                alert("Index in input is too small.")
                return false;
            }
        }
        else{
            alert("Input contains NaN.");
            return false;
        }
            
    }
    if(4<array.length){
        if(array.length<21){
            document.getElementById("min").value = findMin(array).toFixed(2);
            document.getElementById("max").value = findMax(array).toFixed(2);
            document.getElementById("mean").value = calcMean(array).toFixed(2);
            document.getElementById("median").value = calcMedian(array).toFixed(2);
            document.getElementById("mode").value = calcMode(array);
            document.getElementById("stddev").value = calcStdDev(array).toFixed(2);
            document.getElementById("sum").value = calcSum(array).toFixed(2);
            document.getElementById("variance").value = calcVariance(array).toFixed(2);
            

            // var mean = calcMean(array);
            // var median = calcMedian(array);
            // var mode = calcMode(array);
            // var stddev = calcStdDev(array);
            // var sum = calcSum(array);
            // var variance = calcVariance(array);
            // var max = findMax(array);
            // var min = findMin(array);          
            // document.getElementById("mean").value = mean.toFixed(2);
            // document.getElementById("median").value = median.toFixed(2);
            // document.getElementById("mode").value = mode;
            // document.getElementById("stddev").value = stddev.toFixed(2);
            // document.getElementById("sum").value = sum.toFixed(2);
            // document.getElementById("variance").value = variance.toFixed(2);
            // document.getElementById("max").value = max.toFixed(2);
            // document.getElementById("min").value = min.toFixed(2);
            return false;
        }
        else{
            alert("Input array too large.")
            return false;
        }
    }  
    else{
        alert("Input array too small.")
        return false;
    }
}


