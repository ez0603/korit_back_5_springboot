package com.study.mvc.diAndIoc;

import java.util.List;

public class DiService {
    private DiRepository diRepository; // 서비스가 레파지토리를 의존하고 있는 이유 : 레파지토리에서 가져와야 사용할수 있기 때문

    public DiService(DiRepository diRepository) {
        this.diRepository = diRepository;
    }

    public int getTotal() {
        int total = 0;
        List<Integer> scoreList = diRepository.getScoreList();
        for (Integer score : scoreList) {
            total += score;
        }

        return total;
    }

    public double getAverage() {
        double avg = 0;
        int total = 0;
        List<Integer> scoreList = diRepository.getScoreList();
        for (Integer score : scoreList) {
            total += score;
        }
        avg = total / scoreList.size();

        return avg;
    }
}
