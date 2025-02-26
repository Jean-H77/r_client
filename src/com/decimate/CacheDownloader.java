package com.decimate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CacheDownloader implements Runnable {

    public static final String CACHE_LINK = "https://decimate.io/files/DecimateCache.zip";
    private static final String CACHE_VERSION = "https://decimate.io/files/cacheVersion.txt";
    private static final CacheDownloader INSTANCE = new CacheDownloader();
    public static String CACHE_NAME = "DecimateCache";
    private static final String CACHE_PATH = System.getProperty("user.home") + File.separator + CACHE_NAME + File.separator;
    private static final File CACHE_DIRECTORY = new File(CACHE_PATH);

    public static boolean cacheDownloaded = false;
    private CacheDownloader() {

    }

    public static CacheDownloader get() {
        return INSTANCE;
    }

    @Override
    public void run() {
        if (checkCacheUpdate()) {
            try {
                updateCache();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            cacheDownloaded = true;
        }
    }

    private boolean checkCacheUpdate() {
        if (Configuration.localHost) {
            return false;
        }

        File versionFile = new File(CACHE_PATH + "cacheVersion.txt");

        // check if the version file exists
        if (!versionFile.exists()) {
            return true;
        }

        double version = getNewestVersion();
        double current = getCurrentVersion(versionFile.getAbsolutePath());

        // check if cache version has changed
        if (current != version) {
            return true;
        }

        return false;
    }

    private double getCurrentVersion(String url) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(url)));
            String version = reader.readLine();
            reader.close();
            return Double.parseDouble(version);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0.1;
        }
    }

    private double getNewestVersion() {
        try {
            URL url = new URL(CacheDownloader.CACHE_VERSION);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return Double.parseDouble(reader.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    private File downloadCache() {
        File ret = new File(CACHE_PATH + "DecimateCache.zip");

        try (OutputStream out = new FileOutputStream(ret)) {
            URLConnection conn = new URL(CACHE_LINK).openConnection();
            InputStream in = conn.getInputStream();

            long max = conn.getContentLength();
            long curr = 0;

            byte[] b = new byte[1024];

            int len;

            while ((len = in.read(b, 0, b.length)) > -1) {
                out.write(b, 0, len);
                curr += len;
                Client.instance.setLoadingText((int) ((curr * 100) / max), "Downloading cache files - " + (int) ((curr * 100) / max) + "%");
            }

            out.flush();
            in.close();
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            ret.delete();
            return null;
        }
    }

    public void updateCache() throws IOException {
        File clientZip = downloadCache();
        if (clientZip != null) {
            unZip(clientZip);
        }
        try {
            File file = new File(CACHE_PATH + "cacheVersion.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(String.valueOf(getNewestVersion()));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cacheDownloaded = true;
    }

    private void unZip(File clientZip) {
        try {
            unZipFile(clientZip, new File(signlink.findcachedir()));
            Files.delete(clientZip.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unZipFile(File zipFile, File outFile) throws IOException {
        Client.instance.setLoadingText(0, "Unzipping cache files - " + 0 + "%");


        ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

        ZipEntry e;

        long max = 0;
        long curr = 0;

        while ((e = zin.getNextEntry()) != null) {
            max += e.getSize();
        }

        zin.close();

        ZipInputStream in = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

        while ((e = in.getNextEntry()) != null) {
            if (e.isDirectory()) {
                new File(outFile, e.getName()).mkdirs();
            } else {
                FileOutputStream out = new FileOutputStream(new File(outFile, e.getName()));

                byte[] b = new byte[1024];

                int len;

                while ((len = in.read(b, 0, b.length)) > -1) {
                    curr += len;
                    out.write(b, 0, len);
                    Client.instance.setLoadingText((int) ((curr * 100) / max), "Unzipping cache files - " + (int) ((curr * 100) / max) + "%");
                }

                out.flush();
                out.close();
            }
        }

        in.close();
    }
}
