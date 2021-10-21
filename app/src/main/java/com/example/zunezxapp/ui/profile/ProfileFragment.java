package com.example.zunezxapp.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProfileBinding;
import com.example.zunezxapp.ui.changepassword.ChangePasswordFragment;
import com.example.zunezxapp.ui.login.LoginFragment;
import com.example.zunezxapp.ui.login.LoginViewModel;
import com.example.zunezxapp.ultis.RealPathUtil;

import java.util.Random;

public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> implements View.OnClickListener {

    private Uri mUri;

    private Random random = new Random();

    private int updateAva = 0;

    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        updateAva = 1;
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;

                        Glide.with(requireContext()).load(uri).into(binding.imgAvaProfile);
                    }
                }
            }
    );

    @Override
    protected ProfileViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ProfileViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        binding.spinnerGenderProfile.setEnabled(false);
        viewModel.getProfile();
        viewModel.getProfileMutableLiveData().observe(this, it -> {
            if (it != null) {
                binding.edtNameProfile.setEnabled(false);
                binding.icLogout.setVisibility(View.VISIBLE);
                binding.icCancel.setVisibility(View.INVISIBLE);
                binding.icUpdate.setVisibility(View.INVISIBLE);
                binding.imgAvaProfile.setEnabled(false);
                binding.edtEmailUserProfile.setEnabled(false);
                binding.edtAddressUserProfile.setEnabled(false);
                binding.edtBirthdayUserProfile.setEnabled(false);
                binding.spinnerGenderProfile.setEnabled(false);
                binding.edtPhoneUserProfile.setEnabled(false);
                binding.edtBirthdayUserProfile.setText(it.getBirthday());
                binding.edtAddressUserProfile.setText(it.getAddress());
                binding.edtEmailUserProfile.setText(it.getEmail());
                binding.edtPhoneUserProfile.setText(it.getPhone());
                int randomInt = random.nextInt();
                Glide.with(requireContext())
                        .applyDefaultRequestOptions(new RequestOptions()
                                .centerCrop().error(R.drawable.zune_logo)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                        .load(it.getAvatarUrl() + "?" + randomInt)
                        .into(binding.imgAvaProfile);
                if (it.getGender() == 0) {
                    binding.spinnerGenderProfile.setSelection(1);
                } else {
                    binding.spinnerGenderProfile.setSelection(0);
                }
                binding.edtNameProfile.setText(it.getFullName());
            }
        });
    }

    @Override
    protected void initData() {}

    @Override
    protected void initListener() {
        binding.imgAvaProfile.setOnClickListener(this);
        binding.icUpdate.setOnClickListener(this);
        binding.edtBirthdayUserProfile.setOnClickListener(this);
        binding.btnChangePasswordProfile.setOnClickListener(this);
        binding.btnChinhSuaHoSo.setOnClickListener(this);
        binding.icCancel.setOnClickListener(this);
        binding.icLogout.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    @Override
    public void onClick(View view) {
        viewModel.getLoading().observe(this, it -> {
            if (it) {
                loadingDialog.show();
            } else {
                loadingDialog.hide();
            }
        });
        if (view == binding.btnChangePasswordProfile) {
            getVC().addFragment(ChangePasswordFragment.class, null, true, true);
        } else if (view == binding.icLogout) {
            viewModel.logout();
            getVC().replaceFragment(LoginFragment.class, null);
        } else if (view == binding.icCancel) {
            viewModel.getProfile();
        } else if (view == binding.btnChinhSuaHoSo) {
            binding.imgAvaProfile.setEnabled(true);
            binding.edtNameProfile.setEnabled(true);
            binding.icLogout.setVisibility(View.GONE);
            binding.icCancel.setVisibility(View.VISIBLE);
            binding.icUpdate.setVisibility(View.VISIBLE);
            binding.edtEmailUserProfile.setEnabled(true);
            binding.edtAddressUserProfile.setEnabled(true);
            binding.edtBirthdayUserProfile.setEnabled(true);
            binding.spinnerGenderProfile.setEnabled(true);
            binding.edtPhoneUserProfile.setEnabled(true);
        } else if (view == binding.edtBirthdayUserProfile) {
            datePicker.pickDate(binding.edtBirthdayUserProfile);
        } else if (view == binding.icUpdate) {
            if (updateAva == 1) {
                String strRealPath = RealPathUtil.getRealPath(requireActivity(), mUri);
                viewModel.uploadAvatar(strRealPath);
            }
            int gender = 0;
            if (binding.spinnerGenderProfile.getSelectedItem().toString().equals("Nam")) {
                gender = 1;
            }
            viewModel.updateProfile(
                    binding.edtAddressUserProfile.getText().toString().trim(),
                    binding.edtBirthdayUserProfile.getText().toString().trim(),
                    binding.edtEmailUserProfile.getText().toString().trim(),
                    binding.edtNameProfile.getText().toString().trim(),
                    gender,
                    binding.edtPhoneUserProfile.getText().toString().trim()
            );
        } else if (view == binding.imgAvaProfile) {
            if (isPermissionGrandted()) {
                openGallery();
            } else {
                takePermission();
            }
        }
    }

    private boolean isPermissionGrandted() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int readEnternalStoragePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            return readEnternalStoragePermission == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void takePermission() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
            try {
                openGallery11();
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getActivity().getApplicationContext().getPackageName())));
                startActivityForResult(intent, 100);
            }
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String [] {Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private void openGallery11() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse(String.format("package:%s", getActivity().getApplicationContext().getPackageName())));
        startActivityForResult(intent, 100);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 100) {
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                    if (Environment.isExternalStorageManager()) {
                        mUri = data.getData();
                        Toast.makeText(requireContext(), "android 11", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 101) {
                boolean readExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (readExternalStorage) {
                    openGallery();
                } else {
                    takePermission();
                }
            }
        }
    }
}
