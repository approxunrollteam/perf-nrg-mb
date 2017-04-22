package fr.inria.approxloop.perfenergy.smileversions;

/**
 * Created by elmarce on 07/04/17.
 */
public class smile_math_random_UniversalGenerator_92 {

    static final int BIG_PRIME = 899999963;
    int i;
    int j;
    int k;
    int l;
    int ijkl;
    int ij;
    int kl;

    int seed = 100;

    double u[] = new double[97];

    public smile_math_random_UniversalGenerator_92() {
        int ijkl = Math.abs((int) (seed % BIG_PRIME));
        int ij = ijkl / 30082;
        int kl = ijkl % 30082;

        if (ij < 0 || ij > 31328 || kl < 0 || kl > 30081) {
            ij = ij % 31329;
            kl = kl % 30082;
        }

        i = ((ij / 177) % 177) + 2;
        j = (ij % 177) + 2;
        k = ((kl / 169) % 178) + 1;
        l = kl % 169;
    }

    public void benchmark() {
        int m;
        double s, t;

        for (int ii = 0; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }
        //YEAHl
    public void benchmark_PERF() {
        int m;
        double s, t;

        for (int ii = 0; ii < 97; ii+=2) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }

    public void benchmark_NN() {
        int m;
        double s, t;

        for (int ii = 0; ii < 97 - 1; ii+=2) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            u[ii + 1] = s;
        }

        for (int ii = 97 - 1; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }

    public void benchmark_NN4() {
        int m;
        double s, t;

        for (int ii = 0; ii < 97 - 4; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;

            ii++;
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;

            ii++;
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            ii++;
            u[ii] = s;
        }

        for (int ii = 97 - 4; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }

    public void benchmark_NN34() {
        int m;
        double s, t;

        for (int ii = 0; ii < 97 - 4; ii+=4) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            u[ii + 1] = s;
            u[ii + 2] = s;
            u[ii + 3] = s;
        }

        for (int ii = 97 - 1; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }

        }
    }

    public void benchmark_MN() {
        int m;
        double s, t;

        for (int ii = 0; ii < 1; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }

        for (int ii = 2; ii < 97 - 1; ii+=2) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            u[ii -1] = (u[ii] + u[ii -2]) * 0.5;
        }

        for (int ii = 97 - 1; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }

    public void benchmark_MN4() {
        int m;
        double s, t;

        for (int ii = 0; ii < 1; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }

        for (int ii = 4; ii < 97 - 4; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;

            ii++;
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;

            ii+=2;
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            u[ii -1] = (u[ii] + u[ii -2]) * 0.5;
        }

        for (int ii = 97 - 4; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }

    public void benchmark_MN34() {
        int m;
        double s, t;

        for (int ii = 0; ii < 1; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }

        for (int ii = 4; ii < 97 - 4; ii+=4) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
            u[ii - 1] = u[ii - 4] * 0.25 + u[ii] * 0.75;
            u[ii - 2] = (u[ii - 4] + u[ii]) * 0.5;
            u[ii - 3] = u[ii - 4] * 0.75 + u[ii] * 0.25;
        }

        for (int ii = 97 - 4; ii < 97; ii++) {
            s = 0.0;
            t = 0.5;
            for (int jj = 0; jj < 24; jj++) {
                m = (((i * j) % 179) * k) % 179;
                i = j;
                j = k;
                k = m;
                l = (53 * l + 1) % 169;
                if (((l * m) % 64) >= 32) {
                    s += t;
                }
                t *= 0.5;
            }
            u[ii] = s;
        }
    }
}
