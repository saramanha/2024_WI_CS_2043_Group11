import java.io.*;

public class Member {
  private static final String MEMBERS_FILE = "members.txt";

  public static boolean addMember(String uniqueId, String name, String email, boolean hasPaid) {
      String memberDetails = uniqueId + "," + name + "," + email + "," + hasPaid;
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_FILE, true))) {
          writer.write(memberDetails);
          writer.newLine();
          return true;
      } catch (IOException e) {
          e.printStackTrace();
          return false;
      }
  }

  public static boolean changePaymentStatus(String uniqueId) {
    File inputFile = new File(MEMBERS_FILE);
    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
    boolean statusChanged = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] details = currentLine.split(",");
            if (details[0].equals(uniqueId)) {
                // Found the member, toggle their payment status
                boolean hasPaid = Boolean.parseBoolean(details[3]);
                details[3] = String.valueOf(!hasPaid); // Toggle the status
                writer.write(String.join(",", details));
                statusChanged = true;
            } else {
                // Not the member we're looking for, write the line unchanged
                writer.write(currentLine);
            }
            writer.newLine();
        }

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    if (!statusChanged) {
        // The member with the given uniqueId was not found, so no changes were made
        tempFile.delete(); // Clean up the temporary file
        return false;
    }

    // Replace the original file with the updated one
    return inputFile.delete() && tempFile.renameTo(inputFile);
}


  public static String checkStatus(String uniqueId) {
      try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
          String line;
          while ((line = reader.readLine()) != null) {
              if (line.startsWith(uniqueId + ",")) {
                  return line; // Found the member
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null; // Member not found
  }

    


  public static boolean removeMember(String uniqueId) {
    File inputFile = new File(MEMBERS_FILE);
    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String currentLine;
        boolean found = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] split = currentLine.split(",");
            if (!split[0].equals(uniqueId)) {
                writer.write(currentLine + System.lineSeparator());
            } else {
                found = true; // Mark that the member was found and skipped
            }
        }

        if (!found) {
            return false; // Member was not found
        }

    } catch (IOException ex) {
        ex.printStackTrace();
        return false;
    }

    // Delete the original file
    if (!inputFile.delete()) {
        return false;
    }

    // Rename the temp file to the original file
    if (!tempFile.renameTo(inputFile)) {
        return false;
    }

    return true;
}

}
