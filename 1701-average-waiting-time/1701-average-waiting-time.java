class Solution {
    public double averageWaitingTime(int[][] customers) {
        long currentTime = 0;
        long totalWait = 0;

        for (int[] customer : customers) {
            int arrival = customer[0];
            int time = customer[1];

            currentTime = Math.max(currentTime, arrival);
            currentTime += time;

            totalWait += (currentTime - arrival);
        }

        return (double) totalWait / customers.length;
    }
}