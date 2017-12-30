package metadata;

import storageAndExtraction.ExtractData;
import formattingAndOrdering.SeriesNatOrderComparator;

import java.io.File;
import java.util.*;

public class SeriesList implements java.io.Serializable {

    private List<Series> seriesList;

    public SeriesList() {
        seriesList = new ArrayList<>();
    }

    public SeriesList(SeriesList seriesList) {
        this.seriesList = seriesList.getSeriesList();
    }

    public List<Series> getSeriesList() {
        return seriesList;
    }


    public int size() {
        return seriesList.size();
    }

    public void sortSeries() {
        Collections.sort(seriesList, new SeriesNatOrderComparator());
    }

    public void printSeriesList() {
        int i = 1;
        for (Series series : seriesList) {
            System.out.println(i + ": " + series.getName());
            i += 1;
        }
    }

    public void printEpisodeList(int index) {
        seriesList.get(index).printEpisodes();
    }

    public Series getSeries(int userInput) {
        return seriesList.get(userInput);
    }

    public void addSeries(Series toAdd) {
        seriesList.add(toAdd);
        sortSeries();
    }

    public void removeSeries(int index) {
        seriesList.remove(index);
    }

    /**
     *  Combines and returns on SeriesList when given the list saved on file and the file extracted
     */
    public static SeriesList combineSeries(SeriesList seriesSaved, SeriesList extractedList) {
        SeriesList extractedCopy = new SeriesList(extractedList);
        if (!(seriesSaved.isEmpty())) {
            extractedCopy.getSeriesList().removeAll(seriesSaved.getSeriesList());
            extractedCopy.getSeriesList().addAll(seriesSaved.getSeriesList());
            extractedCopy.sortSeries();
        }
        return extractedCopy;
    }


    public boolean containsSeries(String seriesName) {
        for (Series series : seriesList) {
            if (series.getName().equals(seriesName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (seriesList.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SeriesList)) {
            return false;
        }
        SeriesList compare = (SeriesList) other;
        if (this.size() != compare.size()) {
            return false;
        }
        for (Series series : seriesList) {
            if (!(compare.containsSeries(series.getName()))) {
                return false;
            }
        }
        return true;
    }
}

/**
 * TODO: This will normally take the actual initial folder as a series which isn't needed unless
 * there are stray episodes in the folder, one way to fix this would be a method to
 * find this folder and rename it possibly but if its empty delete it
 * but if there are episoedes rename it to 'Unsorted episodes
 */

//TODO: A problem to fix is when this is initially done and then the folders change, so being able to add
// new series while still retaining data of current episode for the rest
