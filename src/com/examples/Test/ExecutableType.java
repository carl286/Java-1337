package com.examples.Test;

public enum ExecutableType {
    OSCAR_MYSQLD("core-mysqld-", "bt-mysqld-", "mysqld", "/rdsdbbin/oscar/bin/mysqld"),

    CSDD("core-csdd-", "bt-csdd-", "csdd", "/rdsdbbin/oscar/bin/csdd"),

    MANFRED("core-postgres-", "bt-postgres-", "postgres", "/rdsdbbin//aurora/bin/postgres");

    private final String corePrefix;
    private final String btPrefix;
    private final String processName;
    private final String executablePath;

    ExecutableType(String corePrefix, String btPrefix, String processName, String executablePath) {
        this.corePrefix = corePrefix;
        this.btPrefix   = btPrefix;
        this.processName = processName;
        this.executablePath = executablePath;
    }

    public String getExecutablePath() {
        return executablePath;
    }

    public String getExecutableFileName() {
        return executablePath.substring(executablePath.lastIndexOf('/') + 1);
    }

    public String getCorePrefix() {
        return corePrefix;
    }

    public String getBtPrefix() {
        return btPrefix;
    }

    public String getProcessName() {
        return processName;
    }

    public static void main(String [] args) {
        System.out.println(OSCAR_MYSQLD.toString());
        System.out.println(CSDD.toString());
        System.out.println(MANFRED.toString());
    }
}
