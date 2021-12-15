package ie.nuigalway.ct326.assignment5;
import java.util.ArrayList;
/**
 * Workspace.java
 * @author Daniel Hannon (19484286)
 */
@SuppressWarnings("unused")
public class Workspace {
	private String workspaceName;
	private String workspaceDescription;
	private UserAccount owner;
	private ArrayList<UserAccount> collaborators;

	/**
	 * Constructor
	 * @param workspaceName name of the workspace
	 * @param workspaceDescription description of the workspace
	 * @param owner UserAccount associated with the owner of the workplace
	 */
	public Workspace(String workspaceName, String workspaceDescription, UserAccount owner) {
		this.workspaceName = workspaceName;
		this.workspaceDescription = workspaceDescription;
		this.owner = owner;
		this.collaborators = new ArrayList<>();
	}

	public String getWorkspaceDescription() {
		return workspaceDescription;
	}

	public String getWorkspaceName() {
		return workspaceName;
	}

	public UserAccount getOwner() {
		return owner;
	}

	public void setCollaborators(ArrayList<UserAccount> collaborators) {
		this.collaborators = collaborators;
	}

	/**
	 * addCollaborator - this adds a collaborator to the already existing ArrayList rather than overwriting it
	 * @param collaborator UserAccount pertaining to someone who collaborates on the project
	 */
	public void addCollaborator(UserAccount collaborator) {
		this.collaborators.add(collaborator);
	}

	/**
	 * toString
	 * @return a well formatted string showcasing the data stored in the class
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder(String.format("Workspace Name:\t%s\nWorkspace Description:\t%s\nOwner:\n", this.workspaceName, this.workspaceDescription));
		String[] temp = this.owner.toString().split("\n");
		for (String line: temp) {
			output.append("\t\t").append(line).append("\n");
		}
		output.append("Collaborators:\n");
		for(UserAccount collaborator:collaborators) {
			temp = collaborator.toString().split("\n");
			for(String line: temp) {
				output.append("\t\t").append(line).append("\n");
			}
			output.append("\n");
		}
		return output.toString();
	}
}
