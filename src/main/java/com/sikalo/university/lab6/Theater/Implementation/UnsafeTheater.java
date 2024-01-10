package com.sikalo.university.lab6.Theater.Implementation;

import com.sikalo.university.lab6.TablePrinter;
import com.sikalo.university.lab6.Theater.SeatIndex;
import com.sikalo.university.lab6.Theater.Theater;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Unsafe Theater implementation that doesn't check user errors
public class UnsafeTheater implements Theater {
    @Getter
    private final int halls;
    @Getter
    private final int rows;
    @Getter
    private final int seats;

    private final int[][][] theaterData;

    public UnsafeTheater(int halls, int rows, int seats) {
        this.halls = halls;
        this.rows = rows;
        this.seats = seats;

        theaterData = new int[halls][rows][seats];
    }

    public void bookSeats(int hallNumber, int row, int... seats) {
        int[] rowData = getRowData(hallNumber, row);

        for (int seat : seats) {
            rowData[seat] = 1;
        }
    }

    public void cancelBooking(int hallNumber, int row, int... seats) {
        int[] rowData = getRowData(hallNumber, row);

        for (int seat : seats) {
            rowData[seat] = 0;
        }
    }

    public boolean isSeatBooked(int hallNumber, int row, int seat) {
        return theaterData[hallNumber][row][seat] == 1;
    }

    public boolean checkAvailability(int hallNumber, int row, int numSeats) {
        int[] rowData = getRowData(hallNumber, row);
        return getRowConsecutiveSeatsIndex(rowData, numSeats, 0) != -1;
    }

    public void printSeatingArrangement(int hallNumber) {
        Integer[][] tableData = Stream.of(getHallData(hallNumber))
                .map(array -> IntStream.of(array).boxed().toArray(Integer[]::new))
                .toArray(Integer[][]::new);

        TablePrinter printer = new TablePrinter(tableData, rows, seats);
        printer.setHighlightPredicate(num -> (Integer) num == 1);
        printer.print();
    }


    public Optional<SeatIndex> findBestAvailable(int hallNumber, int numSeats) {
        SeatIndex bestSeat = new SeatIndex();
        bestSeat.row = -1;
        bestSeat.seat = -1;

        int bestSpanOversize = Integer.MAX_VALUE;

        for (int i = 0; i < rows; i++) {
            int seatIndex = 0;
            int[] rowData = getRowData(hallNumber, i);

            seatIndex = getRowConsecutiveSeatsIndex(rowData, numSeats, seatIndex);

            while (seatIndex != -1) {
                int spanSize = countConsecutiveSeats(rowData, seatIndex);
                int spanOversize = spanSize - numSeats;
                if (spanOversize == 0) {
                    bestSeat.row = i;
                    bestSeat.seat = seatIndex;
                    return Optional.of(bestSeat);
                }

                if (spanOversize < bestSpanOversize) {
                    bestSeat.row = i;
                    bestSeat.seat = seatIndex;
                    bestSpanOversize = spanOversize;
                }

                seatIndex += spanSize + 1;
                seatIndex = getRowConsecutiveSeatsIndex(rowData, numSeats, seatIndex);
            }
        }

        if (bestSeat.seat == -1 || bestSeat.row == -1) {
            return Optional.empty();
        }

        return Optional.of(bestSeat);
    }

    public Optional<SeatIndex> autoBook(int hallNumber, int numSeats) {
        Optional<SeatIndex> bestPlaces = findBestAvailable(hallNumber, numSeats);

        if (bestPlaces.isPresent()) {
            int bestSeatsStartIndex = bestPlaces.get().seat;
            int bestSeatsEndIndex = bestSeatsStartIndex + numSeats;

            int[] seatIndexes = IntStream.range(bestSeatsStartIndex, bestSeatsEndIndex).toArray();
            bookSeats(hallNumber, bestPlaces.get().row, seatIndexes);
        }

        return bestPlaces;
    }

    // Finds index of such a seat in a row that have numSeats unbooked seats after it (including itself).
    // Returns -1 if such a seat is not found
    private int getRowConsecutiveSeatsIndex(int[] rowData, int numSeats, int startIndex) {
        int consecutiveCounter = 0;
        for (int i = startIndex; i < seats; i++) {
            consecutiveCounter = rowData[i] == 0 ? consecutiveCounter + 1 : 0;
            if (consecutiveCounter == numSeats) {
                return i - numSeats + 1;
            }
        }

        return -1;
    }

    private int countConsecutiveSeats(int[] rowData, int startIndex) {
        int consecutiveCounter = 0;
        for (int i = startIndex; i < seats; i++) {
            if (rowData[i] == 1) {
                return consecutiveCounter;
            }
            consecutiveCounter++;
        }
        return consecutiveCounter;
    }

    private int[][] getHallData(int hall) {
        return theaterData[hall];
    }

    private int[] getRowData(int hall, int row) {
        return theaterData[hall][row];
    }
}
