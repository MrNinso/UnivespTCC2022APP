package com.developer.projetounivesp2021_frontrnd.tools;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

public class AsyncTools {
    public static class Promise<T> {
        private final ArrayList<OnAsync<?, ?>> mHandlers = new ArrayList<>();
        public T Input;

        public <R> Promise(OnAsync<T, R> handler) {
            this.mHandlers.add(0, handler);
        }

        public <P, R> Promise<T> then(OnAsync<P, R> handler) {
            this.mHandlers.add(handler);
            return this;
        }

        public void resolve() {
            run();
        }

        public <P, R> void resolve(OnSync<P, R> handler) {
            this.mHandlers.add(handler);
            run();
        }

        private void run() {
            new Thread(() -> {
                Object r = this.Input;
                for (int i = 0; i < mHandlers.size(); i++) {
                    r = ((OnAsync<Object, Object>) mHandlers.get(i)).run(r);
                }
            }).start();
        }

        public interface OnAsync<T, R> {
            R run(T t);
        }

        public interface OnSync<T, R> extends OnAsync<T, R> {
            default R run(T t) {
                new Handler(Looper.getMainLooper()).post(() -> runSync(t));
                return null;
            }

            void runSync(T t);

        }
    }
}
