import java.util.*;

class Solution {
    int n;
    int totalMatches;
    int[][] result;            // 최종 스케줄 저장
    int[] gamesRemaining;      // 각 팀별 남은 경기 수
    boolean[][] matchNeeded;   // matchNeeded[home][away] : 해당 매치가 아직 안 치러졌는지 여부

    public int[][] generateSchedule(int n) {
        // 수학적 분석 결과: N이 3 이하이면 조건을 만족하는 스케줄 생성이 불가능함
        // (필요한 최소 날짜 수가 총 경기 수보다 많아짐)
        if (n <= 3) return new int[0][0];

        this.n = n;
        this.totalMatches = n * (n - 1); // 총 경기 수
        this.result = new int[totalMatches][2];
        this.gamesRemaining = new int[n];
        this.matchNeeded = new boolean[n][n];

        // 초기화: 모든 팀은 2*(N-1) 경기를 치러야 함
        Arrays.fill(gamesRemaining, 2 * (n - 1));
        
        // 초기화: 자기 자신을 제외한 모든 팀과의 (Home, Away) 매치가 필요함
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) matchNeeded[i][j] = true;
            }
        }

        // 백트래킹 시작 (0일차, 어제 경기한 팀 없음: -1, -1)
        if (backtrack(0, -1, -1)) {
            return result;
        }
        
        return new int[0][0];
    }

    // day: 현재 스케줄링하는 날짜 (0 ~ totalMatches-1)
    // prevHome, prevAway: 전날 경기를 치른 팀 (오늘 쉴 수밖에 없음)
    private boolean backtrack(int day, int prevHome, int prevAway) {
        // 모든 날짜의 스케줄을 채웠다면 성공
        if (day == totalMatches) return true;

        // [최적화 1: 가지치기] 
        // 남은 날짜 안에 각 팀이 남은 경기를 소화할 수 있는지 검사
        int daysLeft = totalMatches - day;
        for (int i = 0; i < n; i++) {
            int maxPlayable;
            if (i == prevHome || i == prevAway) {
                // 어제 경기한 팀은 오늘 못 뜀 -> 내일부터 격일로 출전 가능
                // 남은 슬롯: daysLeft / 2
                maxPlayable = daysLeft / 2;
            } else {
                // 어제 쉰 팀은 오늘부터 격일로 출전 가능
                // 남은 슬롯: (daysLeft + 1) / 2
                maxPlayable = (daysLeft + 1) / 2;
            }
            
            // 물리적으로 남은 경기를 다 소화할 수 없다면 이 경로는 실패
            if (gamesRemaining[i] > maxPlayable) {
                return false; 
            }
        }

        // [최적화 2: 후보군 생성 및 정렬]
        // 오늘 치를 수 있는 유효한 경기 후보들을 찾음
        List<int[]> candidates = new ArrayList<>();
        for (int h = 0; h < n; h++) {
            // 어제 경기한 팀은 제외
            if (h == prevHome || h == prevAway) continue;
            
            for (int a = 0; a < n; a++) {
                // 어제 경기한 팀 제외, 자기 자신 제외
                if (a == h || a == prevHome || a == prevAway) continue;

                if (matchNeeded[h][a]) {
                    // 휴리스틱 점수: 두 팀의 '남은 경기 수 합'이 클수록 우선순위 높음
                    // 급한 팀들을 먼저 매칭시켜야 함
                    candidates.add(new int[]{h, a, gamesRemaining[h] + gamesRemaining[a]});
                }
            }
        }

        // 점수가 높은 순(내림차순)으로 정렬하여 그리디하게 탐색
        candidates.sort((x, y) -> y[2] - x[2]);

        for (int[] cand : candidates) {
            int h = cand[0];
            int a = cand[1];

            // 매칭 수행
            result[day][0] = h;
            result[day][1] = a;
            matchNeeded[h][a] = false;
            gamesRemaining[h]--;
            gamesRemaining[a]--;

            // 다음 날짜로 재귀 호출
            if (backtrack(day + 1, h, a)) return true;

            // 실패 시 원상복구 (Backtracking)
            gamesRemaining[a]++;
            gamesRemaining[h]++;
            matchNeeded[h][a] = true;
        }

        return false;
    }
}