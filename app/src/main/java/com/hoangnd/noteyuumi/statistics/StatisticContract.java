package com.hoangnd.noteyuumi.statistics;

public interface StatisticContract {
    interface View{
        void showStatistics(int activeNote, int completedNote);
        void showStatisticsNoneNote(String message);
    }

    interface Presenter{
    }
}
