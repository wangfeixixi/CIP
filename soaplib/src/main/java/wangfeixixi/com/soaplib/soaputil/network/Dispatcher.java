package wangfeixixi.com.soaplib.soaputil.network;



import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
    private int maxRequest = 64;
    private Runnable idleCallback;
    private ExecutorService mExecutorService;
    private Deque<Call> runningSyncCalls = new ArrayDeque();
    private Deque<SoapCall.AsyncCall> readyAsyncCalls = new ArrayDeque();
    private Deque<SoapCall.AsyncCall> runningAsyncCalls = new ArrayDeque();

    public Dispatcher(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    public Dispatcher() {
    }

    public synchronized ExecutorService executorService() {
        if (this.mExecutorService == null) {
            this.mExecutorService = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue());
        }

        return this.mExecutorService;
    }

    public synchronized void enqueue(SoapCall.AsyncCall call) {
        if (this.runningAsyncCalls.size() < this.maxRequest) {
            this.runningAsyncCalls.add(call);
            this.executorService().execute(call);
        } else {
            this.readyAsyncCalls.add(call);
        }

    }

    public synchronized void executed(Call call) {
        this.runningSyncCalls.add(call);
    }

    public void finished(SoapCall.AsyncCall call) {
        this.finished(this.runningAsyncCalls, call, true);
    }

    public void finished(Call call) {
        this.finished(this.runningSyncCalls, call, false);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        synchronized(this) {
            if (!calls.remove(call)) {
                throw new AssertionError("Call wasn't in-flight!");
            } else {
                if (promoteCalls) {
                    this.promoteCalls();
                }

            }
        }
    }

    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequest) {
            if (!this.readyAsyncCalls.isEmpty()) {
                Iterator i = this.readyAsyncCalls.iterator();

                do {
                    if (!i.hasNext()) {
                        return;
                    }

                    SoapCall.AsyncCall call = (SoapCall.AsyncCall)i.next();
                    i.remove();
                    this.runningAsyncCalls.add(call);
                    this.executorService().execute(call);
                } while(this.runningAsyncCalls.size() < this.maxRequest);

            }
        }
    }
}
