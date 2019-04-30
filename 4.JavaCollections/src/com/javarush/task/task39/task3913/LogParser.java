package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery{
    private Path logDir;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
    private Map<String, Integer> records = new HashMap<>();
    {
        records.put("ip", 0);
        records.put("user", 1);
        records.put("date", 2);
        records.put("event", 3);
        records.put("status", 4);
    }

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private boolean isRightDate(Date after, Date before, Date date) {
        return (after == null && before == null) ||
                (before != null && after == null && date.before(before)) ||
                (after != null && date.after(after) && before == null) ||
                (after != null && before != null && date.after(after) && date.before(before));
    }

    private List<String> getLogFileLines(Path path) {
        List<String> lines = new ArrayList<>();
        String line;
        File directory = new File(path.toString());
        File[] files = directory.listFiles();
        for (File entry : files) {
            if (entry.isDirectory()) {
                getLogFileLines(Paths.get(entry.getPath()));
                continue;
            }
            if (entry.getName().endsWith(".log")) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(entry));
                    while ((line = bufferedReader.readLine()) != null) {
                        lines.add(line);
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (isRightDate(after, before, date)) {
                    uniqueIPs.add(logLineParts[0]);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> userIPs = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1])) {
                    if (isRightDate(after, before, date)) {
                        userIPs.add(logLineParts[0]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return userIPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> eventIPs = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (event.toString().equals(logLineParts[3].split(" ")[0])) {
                    if (isRightDate(after, before, date)) {
                        eventIPs.add(logLineParts[0]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return eventIPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> statusIPs = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (status.toString().equals(logLineParts[4])) {
                    if (isRightDate(after, before, date)) {
                        statusIPs.add(logLineParts[0]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return statusIPs;
    }


    @Override
    public Set<String> getAllUsers() {
        Set<String> allUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            allUsers.add(logLineParts[1]);
        }
        return allUsers;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (isRightDate(after, before, date)) {
                    users.add(logLineParts[1]);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<String> eventsOfUser = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1])) {
                    if (isRightDate(after, before, date)) {
                        eventsOfUser.add(logLineParts[3]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return eventsOfUser.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> usersForIP = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (ip.equals(logLineParts[0])) {
                    if (isRightDate(after, before, date)) {
                        usersForIP.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return usersForIP;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> loggedUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.LOGIN.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        loggedUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return loggedUsers;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> downloadedPluginUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.DOWNLOAD_PLUGIN.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        downloadedPluginUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return downloadedPluginUsers;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> wroteMessageUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.WRITE_MESSAGE.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        wroteMessageUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return wroteMessageUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.SOLVE_TASK.toString().equals(logLineParts[3].split(" ")[0])) {
                    if (isRightDate(after, before, date)) {
                        solvedTaskUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> solvedTaskUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.SOLVE_TASK.toString().equals(logLineParts[3].split(" ")[0]) &&
                        task == Integer.parseInt(logLineParts[3].split(" ")[1])) {
                    if (isRightDate(after, before, date)) {
                        solvedTaskUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return solvedTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> doneTaskUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.DONE_TASK.toString().equals(logLineParts[3].split(" ")[0])) {
                    if (isRightDate(after, before, date)) {
                        doneTaskUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> doneTaskUsers = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.DONE_TASK.toString().equals(logLineParts[3].split(" ")[0]) &&
                        task == Integer.parseInt(logLineParts[3].split(" ")[1])) {
                    if (isRightDate(after, before, date)) {
                        doneTaskUsers.add(logLineParts[1]);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return doneTaskUsers;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> datesForUserAndEvent = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (event.toString().equals(logLineParts[3].split(" ")[0]) && user.equals(logLineParts[1])) {
                    if (isRightDate(after, before, date)) {
                        datesForUserAndEvent.add(date);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return datesForUserAndEvent;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> datesWhenSomethingFailed = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Status.FAILED.toString().equals(logLineParts[4])) {
                    if (isRightDate(after, before, date)) {
                        datesWhenSomethingFailed.add(date);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return datesWhenSomethingFailed;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> datesWhenErrorHappened = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Status.ERROR.toString().equals(logLineParts[4])) {
                    if (isRightDate(after, before, date)) {
                        datesWhenErrorHappened.add(date);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return datesWhenErrorHappened;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date dateWhenUserLoggedFirstTime = null;
        Date firstDate = before;
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1]) && Event.LOGIN.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        if (firstDate == null) {
                            firstDate = date;
                            dateWhenUserLoggedFirstTime = firstDate;
                        } else if (date.before(firstDate)) {
                            firstDate = date;
                            dateWhenUserLoggedFirstTime = firstDate;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateWhenUserLoggedFirstTime;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date dateWhenUserSolvedTask = null;
        Date firstDate = before;
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1]) &&
                        Event.SOLVE_TASK.toString().equals(logLineParts[3].split(" ")[0]) &&
                        task == Integer.parseInt(logLineParts[3].split(" ")[1])) {
                    if (isRightDate(after, before, date)) {
                        if (firstDate == null) {
                            firstDate = date;
                            dateWhenUserSolvedTask = firstDate;
                        } else if (date.before(firstDate)) {
                            firstDate = date;
                            dateWhenUserSolvedTask = firstDate;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateWhenUserSolvedTask;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date dateWhenUserDoneTask = null;
        Date firstDate = before;
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1]) &&
                        Event.DONE_TASK.toString().equals(logLineParts[3].split(" ")[0]) &&
                        task == Integer.parseInt(logLineParts[3].split(" ")[1])) {
                    if (isRightDate(after, before, date)) {
                        if (firstDate == null) {
                            firstDate = date;
                            dateWhenUserDoneTask = firstDate;
                        } else if (date.before(firstDate)) {
                            firstDate = date;
                            dateWhenUserDoneTask = firstDate;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateWhenUserDoneTask;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> datesWhenUserWroteMessage = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1]) && Event.WRITE_MESSAGE.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        datesWhenUserWroteMessage.add(date);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return datesWhenUserWroteMessage;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> datesWhenUserDownloadedPlugin = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1]) && Event.DOWNLOAD_PLUGIN.toString().equals(logLineParts[3])) {
                    if (isRightDate(after, before, date)) {
                        datesWhenUserDownloadedPlugin.add(date);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return datesWhenUserDownloadedPlugin;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> allEvents = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (isRightDate(after, before, date)) {
                    allEvents.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return allEvents;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> eventsForIP = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (ip.equals(logLineParts[0])) {
                    if (isRightDate(after, before, date)) {
                        eventsForIP.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return eventsForIP;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> eventsForUser = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (user.equals(logLineParts[1])) {
                    if (isRightDate(after, before, date)) {
                        eventsForUser.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return eventsForUser;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> failedEvents = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Status.FAILED.toString().equals(logLineParts[4])) {
                    if (isRightDate(after, before, date)) {
                        failedEvents.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return failedEvents;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> errorEvents = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Status.ERROR.toString().equals(logLineParts[4])) {
                    if (isRightDate(after, before, date)) {
                        errorEvents.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return errorEvents;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> attemptToSolveTask = new ArrayList<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.SOLVE_TASK.toString().equals(logLineParts[3].split(" ")[0]) && task == Integer.parseInt(logLineParts[3].split( " ")[1])) {
                    if (isRightDate(after, before, date)) {
                        attemptToSolveTask.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return attemptToSolveTask.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        List<Event> successfulAttemptToSolveTask = new ArrayList<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.DONE_TASK.toString().equals(logLineParts[3].split(" ")[0]) && task == Integer.parseInt(logLineParts[3].split( " ")[1])) {
                    if (isRightDate(after, before, date)) {
                        successfulAttemptToSolveTask.add(Event.valueOf(logLineParts[3].split(" ")[0]));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return successfulAttemptToSolveTask.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> allSolvedTaskAndTheirNumber = new HashMap<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.SOLVE_TASK.toString().equals(logLineParts[3].split(" ")[0])) {
                    if (isRightDate(after, before, date)) {
                        int task = Integer.parseInt(logLineParts[3].split(" ")[1]);
                        if (allSolvedTaskAndTheirNumber.containsKey(task)) {
                            allSolvedTaskAndTheirNumber.put(task, allSolvedTaskAndTheirNumber.get(task) + 1);
                        } else {
                            allSolvedTaskAndTheirNumber.put(task, 1);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return allSolvedTaskAndTheirNumber;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> allSolvedTaskAndTheirNumber = new HashMap<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Date date;
            try {
                date = simpleDateFormat.parse(logLineParts[2]);
                if (Event.DONE_TASK.toString().equals(logLineParts[3].split(" ")[0])) {
                    if (isRightDate(after, before, date)) {
                        int task = Integer.parseInt(logLineParts[3].split(" ")[1]);
                        if (allSolvedTaskAndTheirNumber.containsKey(task)) {
                            allSolvedTaskAndTheirNumber.put(task, allSolvedTaskAndTheirNumber.get(task) + 1);
                        } else {
                            allSolvedTaskAndTheirNumber.put(task, 1);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return allSolvedTaskAndTheirNumber;
    }

    public Set<Date> getAllDates() {
        Set<Date> allDates = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            try {
                allDates.add(simpleDateFormat.parse(logLineParts[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return allDates;
    }

    public Set<Status> getAllStatuses() {
        Set<Status> allStatuses = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            allStatuses.add(Status.valueOf(logLineParts[4]));
        }
        return allStatuses;
    }


    @Override
    public Set<Object> execute(String query) {
        String[] queryParts = query.split(" ", 6);
        String field1 = null;
        String field2 = null;
        String value = null;
        String after = null;
        String before = null;
        if (queryParts.length == 2) {
            field1 = queryParts[1];
        }
        if (queryParts.length == 6) {
            field1 = queryParts[1];
            field2 = queryParts[3];
            String[] values = queryParts[5].replace("date between ", "").replace("\"", "").split(" and ");
            if (values.length == 1) {
                value = values[0];
            }
            if (values.length == 3) {
                value = values[0];
                after = values[1];
                before = values[2];
            }
        }
        return getSomthing(field1, field2, value, after, before);
    }

    private Set<Object> getSomthing(String field1, String field2, String value, String after, String before) {
        Set<Object> set = new HashSet<>();
        for (String line : getLogFileLines(logDir)) {
            String[] logLineParts = line.split("\\t");
            Object result = "";
            if (records.get(field1) == 0 || records.get(field1) == 1) {
                result = logLineParts[records.get(field1)];
            }
            if (records.get(field1) == 2) {
                try {
                    result = simpleDateFormat.parse(logLineParts[records.get(field1)]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (records.get(field1) == 3) {
                result = Event.valueOf(logLineParts[records.get(field1)].split(" ")[0]);
            }
            if (records.get(field1) == 4) {
                result = Status.valueOf(logLineParts[records.get(field1)]);
            }
            if (value == null && field2 == null && after == null && before == null) {
                set.add(result);
            } else {
                Date afterDate;
                Date beforeDate;
                Date date;
                try {
                    if (after == null) afterDate = null;
                    else afterDate = simpleDateFormat.parse(after);
                    if (before == null) beforeDate = null;
                    else beforeDate = simpleDateFormat.parse(before);
                    if (logLineParts[2] == null) date = null;
                    else date = simpleDateFormat.parse(logLineParts[2]);
                    if (isRightDate(afterDate, beforeDate, date)) {
                        if (field2.equals("event")) {
                            if (value.equals(logLineParts[records.get(field2)].split(" ")[0])) {
                                set.add(result);
                            }
                        } else if (value.equals(logLineParts[records.get(field2)])) {
                            set.add(result);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }
}