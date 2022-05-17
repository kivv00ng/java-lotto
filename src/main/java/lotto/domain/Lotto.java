package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Lotto {
    public static final int LOTTO_ALL_NUMBER = 45;
    public static final int LOTTO_MINIMUM_NUMBER = 1;

    private List<Integer> lottoNumbers = new ArrayList<>();

    public Lotto() {
        List<Integer> lottoAllNumber = new ArrayList<>();

        IntStream.range(LOTTO_MINIMUM_NUMBER, LOTTO_ALL_NUMBER)
                .forEach(number -> lottoAllNumber.add(number));
        Collections.shuffle(lottoAllNumber);

        lottoAllNumber.stream()
                .limit(6)
                .sorted()
                .forEach(number -> lottoNumbers.add(number));
    }

    public Lotto(List<Integer> lotto) {
        this.lottoNumbers = lotto;
    }

    public int numberOfSame(Lotto lotto) {
        return (int) lotto.lottoNumbers.stream()
                .filter(number -> this.lottoNumbers.contains(number))
                .count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
