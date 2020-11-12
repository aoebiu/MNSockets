package xyz.mengnan.MNSockets.adapter;

import java.io.IOException;

public abstract class AdapterThread extends Thread {

    private final String thread;

    public AdapterThread(Runnable runnable, String thread_type, String thread_name) {
        super(runnable);
        thread = thread_name + thread_type;
        this.setName(thread);
    }

    public AdapterThread(String thread_type, String thread_name) {
        super();
        thread = thread_name + thread_type;
        this.setName(thread);
    }

    public AdapterThread() {
        thread = null;
    }

    public String getThreadName() {
        return thread == null ? this.getName() : thread;
    }

    public abstract void toStart() throws IOException, InterruptedException;

    @Override
    public final void run() {
        try {
            toStart();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
